import java.util.*;

public class RUN{

   

    public static void main(String[] args){

        Scanner entrada = new Scanner(System.in);
        System.out.println("[1] ENTER EMULATOR   [2] EXIT");
        int enterEmulator = entrada.nextInt();
        entrada.nextLine();

        boolean[] PC_Counter = new boolean[3]; 
        boolean[] ACC_valor = new boolean[8];

        while(enterEmulator == 1){

            String action = entrada.nextLine();
            clearScreen();

            System.out.println();
            ROM mostrarROM = new ROM();
           
            if(action.length() >= 2){ // Para evitar un crash
                if(action.substring(0, 3).equals("MOV")){
                    System.out.println("OPERACION MOV");

                }
    
     
                if(action.substring(0, 3).equals("ADD")){
    
                    System.out.println("OPERACION ADD");
    
                    // APARTIR DESDE EL INDICE 4 (indice 3 siempre es un espacio) HASTA LA COMA ES EL QUE QUIERO SUAMRLE
                    String queCosa = "";
                    int indiceComa = 4;
                    for (int i = 4; i < action.length(); i++) {
                        if(action.charAt(i) != ','){
                            queCosa = queCosa + action.charAt(i);
                            indiceComa += 1;
                        }else{
                            indiceComa += 2;
                            break;
                        }
                    }
    
                    // DESPUES DE LA COMA HASTA ACTION.LENGTH ES LO QUE QUIERO SUMAR    
                    String deDondeOQue = "";
                    for (int i = indiceComa; i < action.length(); i++) {
                        deDondeOQue = deDondeOQue + action.charAt(i);
                    }

                    if(queCosa.equals("ACC")){
                        ACC_valor = SUMAyRESTA(queCosa, deDondeOQue, 0, PC_Counter, ACC_valor);
                    }
                    if(queCosa.equals("PC")){
                        PC_Counter = SUMAyRESTA(queCosa, deDondeOQue, 0, PC_Counter, ACC_valor);
                    }


                }
    
                if(action.substring(0, 3).equals("SUB")){
                    System.out.println("OPERACION SUB");


                    // APARTIR DESDE EL INDICE 4 (indice 3 siempre es un espacio) HASTA LA COMA ES EL QUE QUIERO SUAMRLE
                    String queCosa = "";
                    int indiceComa = 4;
                    for (int i = 4; i < action.length(); i++) {
                        if(action.charAt(i) != ','){
                            queCosa = queCosa + action.charAt(i);
                            indiceComa += 1;
                        }else{
                            indiceComa += 2;
                            break;
                        }
                    }
    
                    // DESPUES DE LA COMA HASTA ACTION.LENGTH ES LO QUE QUIERO SUMAR    
                    String deDondeOQue = "";
                    for (int i = indiceComa; i < action.length(); i++) {
                        deDondeOQue = deDondeOQue + action.charAt(i);
                    }

                    if(queCosa.equals("ACC")){
                        ACC_valor = SUMAyRESTA(queCosa, deDondeOQue, 1, PC_Counter, ACC_valor);
                    }
                    if(queCosa.equals("PC")){
                        PC_Counter = SUMAyRESTA(queCosa, deDondeOQue, 1, PC_Counter, ACC_valor);
                    }



                }
    
                if(action.substring(0, 3).equals("AND")){
                    System.out.println("OPERACION AND");


                }
    
                if(action.substring(0, 2).equals("OR")){
                    System.out.println("OPERACION OR");

                }
    
    
                if(action.substring(0, 3).equals("NOT")){
                    System.out.println("OPERACION NOT");

                }
                
                if(action.length() >=4){ // Para evitar un crash
                    if(action.substring(0, 4).equals("LOAD")){
                        System.out.println("OPERACION LOAD");
    
                    }
                }
                


            }


            System.out.println("|--------------------- PC ---------------------|        |----------- ROM ------------|");
            System.out.println("|   PC COUNTER --> " + Arrays.toString(PC_Counter)+"       |" + "        |" + "    R0 -->      " +  turnBooleanToBinary(mostrarROM.ROM_Emulado.get(0).valorEnBooleano) + "    |");
            System.out.println("|----------------------------------------------|        |    R1 -->      " + turnBooleanToBinary(mostrarROM.ROM_Emulado.get(1).valorEnBooleano) + "    |");
            System.out.println("                                                        |    R2 -->      " + turnBooleanToBinary(mostrarROM.ROM_Emulado.get(2).valorEnBooleano) + "    |");
            System.out.println("                                                        |    R3 -->      " + turnBooleanToBinary(mostrarROM.ROM_Emulado.get(3).valorEnBooleano) + "    |");
            System.out.println("                                                        |    R4 -->      " + turnBooleanToBinary(mostrarROM.ROM_Emulado.get(4).valorEnBooleano) + "    |");
            System.out.println("                                                        |    R5 -->      " + turnBooleanToBinary(mostrarROM.ROM_Emulado.get(5).valorEnBooleano) + "    |");
            System.out.println("                                                        |    R6 -->      " + turnBooleanToBinary(mostrarROM.ROM_Emulado.get(6).valorEnBooleano) + "    |");
            System.out.println("                                                        |    R7 -->      " + turnBooleanToBinary(mostrarROM.ROM_Emulado.get(7).valorEnBooleano) + "    |");
            System.out.println("                                                        |----------------------------|");
            System.out.println();
            System.out.println("|-------------------------------------- ACC -----------------------------------------|");
            System.out.println("|   ACC --> " + Arrays.toString(ACC_valor) + "                 |");
            System.out.println("|------------------------------------------------------------------------------------|");




            PC_Counter  = PC(PC_Counter); // aumenta en 1 hasta el 8 (INICIA EN 000 Y AUMENTA HASTA EL 111 Y DE AHI REINICIA A 000)
            
        }

        entrada.close();

      




    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  


    public static class Register{
        String ID;
        boolean[] valorEnBooleano = new boolean[8];

        public Register(String ID, boolean[] valorEnBooleano){
            this.ID = ID;
            this.valorEnBooleano = valorEnBooleano;
        }

    }

    public static class ROM{

        ArrayList<Register> ROM_Emulado = new ArrayList<Register>(); // 16 REGISTROS DE 8 BITS

        // COMO ES ROM TENEMOS QUE NOMBRAR INICIALMENTE LOS IDENTIFICADORES
        public ROM(){

            // Dejo el caso de dejar el ultimo digito en 0 por si se quiere ampliar la ROM
            boolean[] registro0 = {false, false, false, false, false, false, false, true};      // 0000 0001
            boolean[] registro1 = {false, false, false, true, false, false, true, false};       // 0001 0010
            boolean[] registro2 = {false, false, false, true, false, true, false, true};        // 0001 0101
            boolean[] registro3 = {false, false, true, false, false, false, true, true};        // 0010 0011
            boolean[] registro4 = {false, false, true, false, false, false, false, true};       // 0010 0001
            boolean[] registro5 = {false, false, true, true, false, true, true, true};          // 0011 0111
            boolean[] registro6 = {false, false, false, false, false, false, false, false};     // 0000 0000
            boolean[] registro7 = {false, true, false, false, false, false, true, true};        // 0100 0011
            
            boolean[] registro8 = {false, true, false, false, false, true, false, true};        // 0100 0101
            boolean[] registro9 = {false, true, false, true, false, false, false, true};        // 0101 0001
            boolean[] registro10 = {false, true, false, true, false, true, true, true};         // 0101 0111
            boolean[] registro11 = {false, true, true, false, false, false, false, true};       // 0110 0001
            boolean[] registro12 = {false, true, true, false, true, true, true, true};          // 0110 1111
            boolean[] registro13 = {false, true, true, true, false, false, false, true};        // 0111 0001
            boolean[] registro14 = {false, true, true, true, false, true, true, false};         // 0111 0110
            boolean[] registro15 = {false, false, false, false, false, false, false, false};    // 0000 0000 

            ROM_Emulado.add(new Register("R0", registro0));        //  ID 0  , valor 0000 0001 (ADD, 0001)
            ROM_Emulado.add(new Register("R1", registro1));        //  ID 1  , valor 0001 0010 (SUB, 0010)
            ROM_Emulado.add(new Register("R2", registro2));        //  ID 2  , valor 0001 0101 (SUB, 0101)
            ROM_Emulado.add(new Register("R3", registro3));        //  ID 3  , valor 0010 0011 (AND, 0011)
            ROM_Emulado.add(new Register("R4", registro4));        //  ID 4  , valor 0010 0001 (AND, 0001)
            ROM_Emulado.add(new Register("R5", registro5));        //  ID 5  , valor 0011 0111 (OR,  0111)
            ROM_Emulado.add(new Register("R6", registro6));        //  ID 6  , valor 0000 0000 (AND, 0000)
            ROM_Emulado.add(new Register("R7", registro7));        //  ID 7  , valor 0100 0011 (LOAD,0011)
           
            ROM_Emulado.add(new Register("R8", registro8));        //  ID 8  , valor 0100 0101 (LOAD,0101)   
            ROM_Emulado.add(new Register("R9", registro9));        //  ID 9  , valor 0101 0001 (MOV, 0001)
            ROM_Emulado.add(new Register("R10", registro10));      //  ID 10 , valor 0101 0111 (MOV, 0111)
            ROM_Emulado.add(new Register("R11", registro11));      //  ID 11 , valor 0110 0001 (JUMP,0001) 
            ROM_Emulado.add(new Register("R12", registro12));      //  ID 12 , valor 0110 1111 (JUMP,1111) 
            ROM_Emulado.add(new Register("R13", registro13));      //  ID 13 , valor 0111 0001 (NOP, 0001)
            ROM_Emulado.add(new Register("R14", registro14));      //  ID 14 , valor 0111 0110 (NOP, 0000)
            ROM_Emulado.add(new Register("R15", registro15));      //  ID 15 , valor 0000 0000 (ADD, 0000)

        }




    }

    public static String turnBooleanToBinary(boolean[] valorBooleano){
        String valorBinario = "";
        for (int i = 0; i < valorBooleano.length; i++) {
            if(valorBooleano[i] == false){
                valorBinario = valorBinario + "0";
            }else{
                valorBinario = valorBinario + "1";
            }
        }
        return valorBinario;
    }

    public static boolean[] SUMAyRESTA(String queCosa, String deDondeOQue, int sumaOresta, boolean[] PC, boolean[] ACC){

        // Supongamos que simplemente es sumar dos valores
        // Tenemos que revisar si algun valor esta en decimal o en hexadecimal, para convertirlo a binario y hacer la suma binaria

        boolean[] valorBinarioBooleanoINPUT = new boolean[8];

        if(deDondeOQue.charAt(0) == '#'){ //NUMERO DECIMAL
            System.out.println("DECIMAL");
            int valor_decimalPorSumar = Integer.parseInt(deDondeOQue.substring(1, deDondeOQue.length())); // Convierto mi String a Entero
            String valor_binario = Integer.toBinaryString(valor_decimalPorSumar);                         // Convierto el entero a un String binario
            valorBinarioBooleanoINPUT = binaryToBooleanArray(valor_binario, 4);                         // Convierto el binario a un array de booleanos
        }else if(deDondeOQue.charAt(deDondeOQue.length() - 1) == 'h'){ // NUMERO HEXADECIMAL
            System.out.println("HEXADECIMAL");
            int valor_decimalPorSumar = Integer.parseInt(deDondeOQue.substring(0, deDondeOQue.length()-1), 16); // Convierto de hex a decimal
            String valor_binario = Integer.toBinaryString(valor_decimalPorSumar);                               // Convierto el entero a un String binario
            valorBinarioBooleanoINPUT = binaryToBooleanArray(valor_binario, 4);                               // Convierto el binario a un array de booleanos
        }else{ // DESDE UNA DIRECCION EN LA ROM
            System.out.println("DE DIRECCION");
            String direccion = deDondeOQue; // Cnovierto mi String a un valor Entero para asi buscar la direccion en la ROM
            valorBinarioBooleanoINPUT = buscarEnROM(direccion);
        }


        

        if(sumaOresta == 0){ // SE HACE UNA SUMA
            if(queCosa.equals("ACC")){
                // Lo que haya en el accumulador le sumo lo de valorBinarioINPUT (que es el input ya convertido a un bool)
                ACC = sumaEntreDosBooleanArrays(ACC, valorBinarioBooleanoINPUT, 8);
                return ACC;
            }else if(queCosa.equals("PC")){
                PC = sumaEntreDosBooleanArrays(PC, valorBinarioBooleanoINPUT, 3);
                return PC;
            }
        }else{ // SE HACE UNA RESTA
            if(queCosa.equals("ACC")){
                // Lo que haya en el accumulador le sumo lo de valorBinarioINPUT (que es el input ya convertido a un bool)
                ACC = restaEntreDosBooleanArrays(ACC, valorBinarioBooleanoINPUT, 8);
                return ACC;
            }else if(queCosa.equals("PC")){
                PC = restaEntreDosBooleanArrays(PC, valorBinarioBooleanoINPUT, 3);
                return PC;
            }
        }


        return null;

    

    }

    public static boolean[] binaryToBooleanArray(String binario, int tamanio){
        int test = Integer.parseInt(binario, 2);
        

        if(tamanio == 4){
            if(test > 15){ // Si el valor ingresado es mayor a 15 entonces por default se lo dejo en 15 y no tomo mas
                binario = "1111";
            }
        }

        if(tamanio == 8){
            if(test > 255){ // Si el valor ingresado es mayor a 15 entonces por default se lo dejo en 15 y no tomo mas
                binario = "11111111";
            }
        }

        while(binario.length() != tamanio){
            binario = "0" + binario;
        }

    
        boolean[] valorBinarioBooleano = new boolean[tamanio];
        for (int i = 0; i < binario.length(); i++) {
            if(binario.charAt(i) == '0'){
                valorBinarioBooleano[i] = false;
            }else{
                valorBinarioBooleano[i] = true;
            }
        }
        
        return valorBinarioBooleano;
    }

    public static boolean[] buscarEnROM(String buscarID){
        ROM rom = new ROM();
        boolean[] instruccionesYvalor = new boolean[8];

        for (int i = 0; i < rom.ROM_Emulado.size(); i++) {
            if(rom.ROM_Emulado.get(i).ID.equals(buscarID)){
                instruccionesYvalor = rom.ROM_Emulado.get(i).valorEnBooleano;
            }
        }

        return instruccionesYvalor;
    }

    public static boolean[] PC(boolean[] PC_Counter){

        String valorBinarioNuevo="";
        String PC_CounterString = turnBooleanToBinary(PC_Counter);
        int valorDecimal = Integer.parseInt(PC_CounterString, 2);
        valorDecimal += 1;

        if(valorDecimal == 8){
            valorBinarioNuevo = "000";
        }else{
            valorBinarioNuevo = Integer.toBinaryString(valorDecimal);
        }
        
        boolean[] PC_actualizado = binaryToBooleanArrayPC(valorBinarioNuevo);

        return PC_actualizado;
    }

    public static boolean[] binaryToBooleanArrayPC(String binary){
        boolean[] valorBinarioBooleano = new boolean[3];

        if(binary.equals("1")){
            binary = "001";
        }
        if(binary.equals("10")){
            binary = "010";
        }
        if(binary.equals("11")){
            binary = "011";
        }


        for (int i = 0; i < binary.length(); i++) {
            if(binary.charAt(i) == '0'){
                valorBinarioBooleano[i] = false;
            }else{
                valorBinarioBooleano[i] = true;
            }
        }
        
        return valorBinarioBooleano;

    }

    public static boolean[] sumaEntreDosBooleanArrays(boolean[] elSumado, boolean[] loQueSuma, int tamanio){
        boolean[] resultado = new boolean[elSumado.length];
        
        String binario_elSumado = turnBooleanToBinary(elSumado);            // Convierto en binario el array de booleanos
        String binario_loQueSuma = turnBooleanToBinary(loQueSuma);          // Convierto en binario el array de booleanos
        int decimal_elSumado = Integer.parseInt(binario_elSumado, 2);       // Convierto en decimal el valor binario
        int decimal_loQueSuma = Integer.parseInt(binario_loQueSuma, 2);     // Convierto en decimal el valor binario
        int total = decimal_elSumado + decimal_loQueSuma;                   // Hago la suma en decimal
        String total_Binario = Integer.toBinaryString(total);               // Convierto mi total a un string binario

        while(total_Binario.length() != elSumado.length){
            // Tengo que concatenar 0s hasta que tenga un string de N caracteres
            total_Binario = "0" + total_Binario;
        }

        resultado = binaryToBooleanArray(total_Binario, tamanio);
    
        return resultado;
    }

    public static boolean[] restaEntreDosBooleanArrays(boolean[] elRestado, boolean[] loQueResta, int tamanio){
        boolean[] resultado = new boolean[elRestado.length];

        String binario_elRestado = turnBooleanToBinary(elRestado);            // Convierto en binario el array de booleanos
        String binario_loQueResta = turnBooleanToBinary(loQueResta);          // Convierto en binario el array de booleanos
        int decimal_elRestado = Integer.parseInt(binario_elRestado, 2);       // Convierto en decimal el valor binario
        int decimal_loQueResta = Integer.parseInt(binario_loQueResta, 2);     // Convierto en decimal el valor
        int total = decimal_elRestado - decimal_loQueResta;                     // Hago la resta en decimal
        String total_Binario = Integer.toBinaryString(total);               // Convierto mi total a un string binario

        while(total_Binario.length() != elRestado.length){
            // Tengo que concatenar 0s hasta que tenga un string de N caracteres
            total_Binario = "0" + total_Binario;
        }

        resultado = binaryToBooleanArray(total_Binario, tamanio);

        return resultado;
    }


}