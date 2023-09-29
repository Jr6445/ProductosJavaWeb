    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package EJB;

    import Entity.Producto;
    import java.util.List;
    import javax.ejb.Stateless;
    import javax.faces.application.FacesMessage;
    import javax.faces.context.FacesContext;
    import javax.persistence.EntityManager;
    import javax.persistence.PersistenceContext;
    import javax.persistence.Query;
    import com.mycompany.libreriaoperacione.OperacionesMatematicasImpl;


    /**
     *
     * @author Riveraa
     */
    @Stateless
    public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

        @PersistenceContext(unitName = "ProductoPU")
        private EntityManager em;

         OperacionesMatematicasImpl op = new OperacionesMatematicasImpl();
         
         //usando metodos de la libreria que creamos anteriormente
         public void suma(){
            double result = op.suma(1, 2);
             System.out.println(result);
         }
         
         public void resta(){
            double result = op.resta(1, 2);
             System.out.println(result);
         }
         
         public void division(){
            double result = op.division(1, 2);
             System.out.println(result);
         }
         
         public void multiplicacion(){
            double result = op.multiplicacion(1, 2);
             System.out.println(result);
         }
         
         /////////////////////////////////////////////////////////////////////////////////
         
        @Override
        public EntityManager getEntityManager() {
            return em;
        }

        public ProductoFacade() {
            super(Producto.class);
        }

        public void testConnection() {
            try {
                // Usando el EntityManager de esta clase
                Query query = em.createNativeQuery("SELECT * FROM productos where id = 1");
                List result = query.getResultList();
                if (!result.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Conexión exitosa", ""+result.toString()));
                    System.out.println(result.toString());
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, 
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "La consulta no devolvió resultados", ""));
                }
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conexión: " + e.getMessage(), ""));
            }
        }


    }
