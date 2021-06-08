
package Presentation.Command;

import BLL.ParcelaBLL;
import BLL.UsuarioBLL;
import BLL.ValidacionFormulario;
import Entidades.Usuario;
import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lidia Baixauli de la Villa
 */
public class DarAltaUsuarioCommand extends ICommand{

    @Override
    //si da error volver a cargar listado de parcelas disponibles
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ParcelaBLL parcelaBLL = new ParcelaBLL();
        boolean validaDatos = true;
        int asignaParce;
        String paginaSig = "/menus/menuGestionAdmin.jsp";
        int telefono = 0;
        String nombre = (request.getParameter("nombre")).trim();
        String email = (request.getParameter("email")).trim();
        String password = (request.getParameter("pass")).trim();
        String tele =  (request.getParameter("telefono")).trim();
        int rolAsignado =  Integer.parseInt(request.getParameter("roles"));
        //System.out.println("rol"+ rolAsignado);
       
        String parcela = request.getParameter("parcela");
        
        //nombre a utf 8 por si hay caracteres especiales
        byte[] ptext = nombre.getBytes(ISO_8859_1); 
        String nombreUtf = new String(ptext, UTF_8); 
       
        //por si hay fallos que cargue lo introducido
        Usuario user = new Usuario();
        user.setNombre(nombreUtf);
        user.setEmail(email);


        //validar si los campos son correctos, se comprueban todos
        ValidacionFormulario validaForm = new ValidacionFormulario();
        if(!validaForm.validaNombresCompuestos(nombreUtf)){
            request.setAttribute("errorNom", "noOk");
            validaDatos=false;
            //valida datos false
        }
        
        //valida email
        if(!validaForm.validaEmail(email)){
            request.setAttribute("errorEmail", "noOk");
            validaDatos=false;
        }

        //valida contraseña. Ampliación codificarla
        if(!validaForm.validaPass(password)){
            request.setAttribute("errorPass", "noOk");
            validaDatos=false;
        }
       
        //valida tlf si se ha introducido
         if(!tele.equals("") && tele!=null){
            if(!validaForm.validaTfn(tele)){
                request.setAttribute("errorTlf", "noOk");
                validaDatos=false;
            
            }else{
                telefono =  Integer.parseInt(tele);
                user.setTelefono(telefono);
            }
        }

        //guarda usuario si validación ok y dar de alta
        if(validaDatos){
            //Usuario user = new Usuario();
            user.setNombre(nombreUtf);
            user.setEmail(email);
            //encripta password
              //encriptar contraseña
            String sha256hex = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            user.setPassword(sha256hex);
            user.setTelefono(telefono);
            user.setRol(rolAsignado);
            UsuarioBLL userBLL = new UsuarioBLL();
            //ver si ya existe
            if(userBLL.buscaUsuarioEmail(user)==null){
                //guarda en BBDD
                if(userBLL.altaNueva(user)==1){//alta ok
                    //System.out.println("altaok");
                    //request.setAttribute("altaOk", "Usuario guardado con éxito");
                    request.setAttribute("altaOk", user);
                    if(parcela!=null){                        
                        asignaParce = parcelaBLL.asignaUsuario(parcela, user);
                        if(asignaParce==1){
                            request.setAttribute("asignaParceOk", parcela);
                        }
                    }
                }
                    
            }else{
                request.setAttribute("errorYaRegistrado", "Este usuario ya existe");
                //vuelve a cargar lista parcelas                
                request.setAttribute("listaParcelasDisp", parcelaBLL.listaParcelasDisp());
                paginaSig = "/altaUsuario.jsp";
            }
        }else{//redirige al formulario y muestra errores
            request.setAttribute("listaParcelasDisp", parcelaBLL.listaParcelasDisp());
            //manda datos que se habían rellenado
            request.setAttribute("datos", user);
            paginaSig = "/altaUsuario.jsp";
            //vuelve a cargar lista parcelas
        }
        
        //si hay parcela, asignarla al usuario
        //System.out.println(request.getParameter("parcela"));
 
        return paginaSig;
    }
    

    
}
