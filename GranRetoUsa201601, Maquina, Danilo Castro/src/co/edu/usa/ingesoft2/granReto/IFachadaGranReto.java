package co.edu.usa.ingesoft2.granReto;

public interface IFachadaGranReto
{
   public void cargarArchivo( String rutaArchivo ) throws GranRetoException;
   
   public String calcular();
}
