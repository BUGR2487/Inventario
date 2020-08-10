package Tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    /***
     * Clase que contiene las propiedades del archivo <b>configurations.properties <i>(Data/config.properties)</i></b>
     *
     * @author imSoft.
     *
     */
    public static class Propiedad{
        private String key;
        private String defaultValue;

        public Propiedad(String key, String defaultValue)
        {
            this.setKey(key);
            this.setDefaultValue(defaultValue);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }
    }

    public static class ReadException extends Exception{

        public ReadException(String msg) {
            super(msg);
        }
    }

    public static class EmptyProperty extends Exception{

        public EmptyProperty(String msg) {
            super(msg);
        }
    }

    // Constantes para invocar el parametro del archivo:

    public static final Propiedad	JDBC_URL 			= new Propiedad("mysql.jdbcUrl", "");
    public static final Propiedad	JDBC_HOST 			= new Propiedad("mysql.host",    "localhost");
    public static final Propiedad	JDBC_PORT 			= new Propiedad("mysql.port",    "3306");
    public static final Propiedad	JDBC_DB 			= new Propiedad("mysql.db",      "inventario");
    public static final Propiedad	MYSQL_USER 			= new Propiedad("mysql.user",    "root");
    public static final Propiedad	MYSQL_PASS 			= new Propiedad("mysql.pass",    "root");

    //Variables de la clase:
    private static Config instance = null;

    private Properties cnf = null;


    private Config() throws ReadException {
        if (this.getCnf() == null)
            this.setCnf(new Properties());
        try {
            InputStream lector = new FileInputStream("./Data/config.properties");
            this.getCnf().load(lector);
        }catch (IOException e){
            throw new ReadException("Error, al leer el archivo de configuraciones.");
        }
    }

    public Properties getCnf() {
        return cnf;
    }

    public void setCnf(Properties cnf) {
        this.cnf = cnf;
    }

    public static Config getInstance() throws ReadException {

        if(instance == null)
           instance = new Config();

        return instance;
    }
    public String getProperty(Propiedad propiedad)
    {
        if(propiedad.getKey().equals(JDBC_URL.getKey()))
        {
            String host = this.getProperty(JDBC_HOST, "");
            String port = this.getProperty(JDBC_PORT, "");
            String db   = this.getProperty(JDBC_DB, "");
            String url  = this.getProperty(JDBC_URL, "");

            return url.replace("${mysql.host}", host)
                    .replace("${mysql.port}",port)
                    .replace("${mysql.db}", db);
        }

            return this.getProperty(propiedad, "");


    }

    public String getProperty(Propiedad propiedad, String dft)
    {
        //te dejo unos comentarios por si en dado caso desconoces los ternarios en java, una referencia: https://www.guj.com.br/t/operador-condicional-ternario/49073
        //Checho el archivo y verifico si existe la propiedad o si no retorno un valor por default:
        return this.cnf.getProperty(propiedad.getKey(), (dft != null)?//CONDICION 1: de cumplirse paso a la CONDICION 2
                                                            (dft.isEmpty())?//CONDICION 2: en caso de ser true se envia de parametro dft
                                                                    dft
                                                                    :propiedad.getDefaultValue() // de no cumplir con la CONDICION 2, entonces se envia el valor por default de la variable propiedad
                                                            :propiedad.getDefaultValue());//de no cumplirse la CONDICION 1, entonces envio de parametro el valor por default de la variable propiedad
    }
}
