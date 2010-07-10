package simuladorcolas;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *
 * @author Fabián Guevara
 * @date 6-7-10
 * @version 1.0
 * La clase simulador contiene toda la lógica para una simulación de colas con
 * un solo servidor, 3 tipos de clientes con tiempos de atención
 * exponencialmente distribuido con media ajustable (igual para todos), y
 * tiempos de llegada también exponencialmente distribuidos, con media ajustable
 * para cada cliente individualmente. También el tiempo de la simulación es
 * ajustable.
 */
public class Simulador
{
    /**
     * Tiempo de llegada esperado de los clientes tipo 1
     */
    private final double lambda1;
    /**
     * Tiempo de llegada esperado de los clientes tipo 2
     */
    private final double lambda2;
    /**
     * Tiempo de llegada esperado de los clientes tipo 3
     */
    private final double lambda3;
    /**
     * Tiempo de atención esperado de los 3 tipos de cliente
     */
    private final double miu;
    /**
     * En cada momento de la simulación, indica el tiempo actual
     */
    private double horaSimulacion; //TM
    /**
     * Si true, el servidor puede atender de inmediato al primero en la cola
     * (o el próximo en llegar, si la cola está vacía). Si false, significa
     * que el servidor está ocupado. Mientras el servidor se mantenga así, todo
     * cliente que llegue tendrá que esperar
     */
    private boolean servidorDisponible; // SS
    /**
     * Tiempo en el que se acaba la simulación
     */
    private final double tiempoLimiteSimulacion; // MX
    /**
     * Conjunto de listas, una para cada tipo de cliente. La i-ésima lista
     * contiene el tiempo que esperó cada cliente tipo i en el sistema para ser
     * atendido
     */
    private HashMap<Cliente.TIPO, ArrayList<Double>> tiemposEspera;
    /**
     * Lista con la longitud de la cola al momento de cada llegada y cada salida
     * durante la simulación
     */
    private ArrayList<Integer> longitudesCola;
    /**
     * Lista con los clientes en la cola y en el sistema. La cabeza de la cola
     * representa al cliente siendo atendido.
     */
    private ArrayList<Cliente> cola;
    /**
     * Generador de números aleatorios
     */
    private final GeneradorAleatorios generador;

    private double longitudEsperadaCola;
    private double tiempoEsperaMedio1;
    private double tiempoEsperaMedio2;
    private double tiempoEsperaMedio3;
    /**
     * Crea un nuevo objeto Simulador indicando la media de los tiempos de
     * llegada para cada cliente, la media del tiempo de atención y el tiempo
     * de la simulación.
     * @param l1 tiempo esperado de llegada de los clientes de tipo 1
     * @param l2 tiempo esperado de llegada de los clientes de tipo 2
     * @param l3 tiempo esperado de llegada de los clientes de tipo 3
     * @param t tiempo que tardará la simulación
     */
    public Simulador(double l1, double l2, double l3, double m, double t)
    {
        this.lambda1 = l1;
        this.lambda2 = l2;
        this.lambda3 = l3;
        this.miu = m;
        this.horaSimulacion = 0;
        this.servidorDisponible = true;
        this.tiempoLimiteSimulacion = t;
        this.tiemposEspera = new HashMap<Cliente.TIPO, ArrayList<Double>>();
        tiemposEspera.put(Cliente.TIPO.UNO, new ArrayList<Double>());
        tiemposEspera.put(Cliente.TIPO.DOS, new ArrayList<Double>());
        tiemposEspera.put(Cliente.TIPO.TRES, new ArrayList<Double>());
        this.longitudesCola = new ArrayList<Integer>();
        this.cola = new ArrayList<Cliente>();
        this.generador = new GeneradorAleatorios();
        generador.setSemilla(System.currentTimeMillis());
        this.longitudEsperadaCola = this.tiempoEsperaMedio1
                = this.tiempoEsperaMedio2 = this.tiempoEsperaMedio3 = 0;
    }
    /**
     * Inicia la simulación
     */
    public void simular()
    {
        //generar tiempos de llegada
        double llegada1 = this.generador.nextExponencial(this.lambda1);
        double llegada2 = this.generador.nextExponencial(this.lambda2);
        double llegada3 = this.generador.nextExponencial(this.lambda3);
        double tiempoSalida = Long.MAX_VALUE;
        TIEMPO_EVENTO menor = this.getMenor(tiempoSalida, llegada1, llegada2, llegada3);
        Cliente cliente = null;
        do
        {
            if(menor == TIEMPO_EVENTO.PROXIMA_SALIDA)
            {
                {//simular paso del tiempo
                this.horaSimulacion += tiempoSalida;
                llegada1 -= tiempoSalida;
                llegada2 -= tiempoSalida;
                llegada3 -= tiempoSalida;
                this.actualizarTiempoClientes(tiempoSalida);
                }
                if(this.cola.size() == 1) //ojo, la cabeza es el que está siendo atendido
                {   
                    this.servidorDisponible = true;
                    tiempoSalida = Long.MAX_VALUE;
                }
                else// o sea, cola.size() > 1 (si fuera menor que uno, no estaríamos aquí
                {
                    tiempoSalida = this.generador.nextExponencial(this.miu);
                }
                //guardar en la lista respectiva el tiempo del cliente que ya va a salir
                this.tiemposEspera.get(this.cola.get(0).getTipo()).add(this.cola.get(0).getTiempo());
                this.cola.remove(0); //sale la cabeza (el que estaba siendo atendido)
                this.longitudesCola.add(this.cola.size());
                //TODO manejar lógica cuando hay una salida
            }
            else
            {
                switch(menor)//decidir cuál es el próximo evento
                {
                    case LLEGADA_CLIENTE1:
                        {//simular paso del tiempo
                        this.horaSimulacion += llegada1;
                        llegada2 -= llegada1;
                        llegada3 -= llegada1;
                        tiempoSalida -= llegada1;
                        this.actualizarTiempoClientes(llegada1);
                        }
                        cliente = new Cliente(Cliente.TIPO.UNO);
                        llegada1 = this.generador.nextExponencial(this.lambda1);
                        break;
                    case LLEGADA_CLIENTE2:
                        {//simular paso del tiempo
                        this.horaSimulacion += llegada2;
                        llegada1 -= llegada2;
                        llegada3 -= llegada2;
                        tiempoSalida -= llegada2;
                        this.actualizarTiempoClientes(llegada2);
                        }
                        cliente = new Cliente(Cliente.TIPO.DOS);
                        llegada2 = this.generador.nextExponencial(this.lambda2);
                        //TODO encargarse del evento de llegar un cliente tipo 2
                        break;
                    case LLEGADA_CLIENTE3:
                        {//simular paso del tiempo
                        this.horaSimulacion += llegada3;
                        llegada1 -= llegada3;
                        llegada2 -= llegada3;
                        tiempoSalida -= llegada3;
                        this.actualizarTiempoClientes(llegada3);
                        }
                        cliente = new Cliente(Cliente.TIPO.TRES);
                        llegada3 = this.generador.nextExponencial(this.lambda3);
                        //TODO encargarse del evento de llegar un cliente tipo 3
                        break;
                }
                if(this.servidorDisponible)
                {
                    this.cola.add(0, cliente);
                    this.servidorDisponible = false;
                    tiempoSalida = this.generador.nextExponencial(this.miu);
                }
                else
                {
                    this.cola.add(cliente);
                }
                this.longitudesCola.add(this.cola.size());
                //TODO generar siguiente llegada
            }
            menor = this.getMenor(tiempoSalida, llegada1, llegada2, llegada3);
        }while(this.horaSimulacion < this.tiempoLimiteSimulacion);
        //TODO lógica de la simulación
    }
    enum TIEMPO_EVENTO {PROXIMA_SALIDA, LLEGADA_CLIENTE1, LLEGADA_CLIENTE2, LLEGADA_CLIENTE3};
    /**
     * Indica cuál es el menor de entre 4 valores reales
     * @return
     * <ul>
     * <li>TIEMPO_EVENTO.PROXIMA_SALIDA si tiempoSalida es el menor</li>
     * <li>TIEMPO_EVENTO.LLEGADA_CLIENTEi si li es el menor</li>
     */
    private TIEMPO_EVENTO getMenor(double tiempoSalida, double l1, double l2, double l3)
    {
        TIEMPO_EVENTO resultado = TIEMPO_EVENTO.PROXIMA_SALIDA;
        double menor = tiempoSalida;
        if(l1 < menor)
        {
            menor = l1;
            resultado = TIEMPO_EVENTO.LLEGADA_CLIENTE1;
        }
        if(l2 < menor)
        {
            menor = l2;
            resultado = TIEMPO_EVENTO.LLEGADA_CLIENTE2;
        }
        if(l3 < menor)
        {
            resultado = TIEMPO_EVENTO.LLEGADA_CLIENTE3;
        }
        return resultado;
    }
    /**
     * Incrementa, en una constante dada, la propiedad tiempo de cada cliente
     * en el sistema
     * @param tiempo valor que se suma al tiempo de cada cliente
     */
    private void actualizarTiempoClientes(double tiempo)
    {
        for(Cliente c : this.cola)
        {
            c.actualizarTiempo(tiempo);
        }
    }
    /**
     * Calcula la longitud media de la cola, y el tiempo de espera medio para
     * cada tipo de cliente. 
     */
    private void hacerEstadistica()
    {
        //calcular longitud de cola media
        for(int longitud : this.longitudesCola)
        {
            this.longitudEsperadaCola += longitud;
        }
        this.longitudEsperadaCola /= this.longitudesCola.size();
        //calcular tiempo de espera promedio para cada tipo de cliente
        //for(ArrayList<)
    }
    public double getLongitudColaEsperada()
    {
        return this.longitudEsperadaCola;
    }
    public double getTiempoEsperaMedio1()
    {
        return this.tiempoEsperaMedio1;
    }
    public double getTiempoEsperaMedio2()
    {
        return this.tiempoEsperaMedio2;
    }
    public double getTiempoEsperaMedio3()
    {
        return this.tiempoEsperaMedio3;
    }
    /**
     * Esta clase abstrae las propiedades de un cliente. Básicamente el tiempo
     * que lleva en el sistema y el tipo. Para esta segunda propiedad, esta
     * clase define una enum pública llamada TIPO.
     */
    static class Cliente
    {
        public enum TIPO {UNO, DOS, TRES};
        TIPO tipo;
        private double tiempo;

        public Cliente(TIPO t)
        {
            this.tipo = t;
            this.tiempo = 0;
        }
        /**
         * Incrementa el tiempo del cliente
         * @param t incremento del tiempo del cliente
         */
        public void actualizarTiempo(double t)
        {
            this.tiempo += t;
        }
        public double getTiempo()
        {
            return this.tiempo;
        }
        public TIPO getTipo()
        {
            return this.tipo;
        }
    }
}
