package app;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

import org.json.JSONObject;

public class RequestProcessor implements Runnable{
    private Socket socket= null;
    private OutputStream os = null;
    private BufferedReader in = null;
    private DataInputStream dis = null;
    private String msgToClient = "HTTP/1.1 200 OK\n"
            + "Server: HTTP server/0.1\n"
            + "Access-Control-Allow-Origin: *\n\n";
    private JSONObject jsonObject = new JSONObject();

    public RequestProcessor(Socket socket) {
        super();
        try {
            this.socket = socket;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            os = socket.getOutputStream();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        try {
        //write code here
            String httpRequestLine = in.readLine();

            String[] httpRequestElements = httpRequestLine.split(" ");
            httpRequestElements[1] = httpRequestElements[1].substring(httpRequestElements[1].indexOf("?")+1);

            String[] params = httpRequestElements[1].split("&");
            HashMap<String, String> operands = new HashMap<>();

            for (String param: params) {
                String[] elements = param.split("=");
                operands.put(elements[0],elements[1]);
            }

            Double operandOne = 0.0 ;
            Double operandTwo = 0.0 ;
            operandOne = Double.parseDouble(operands.get("leftOperand"));
            operandTwo = Double.parseDouble(operands.get("rightOperand"));
            Double result = 0.0;

            operands.putIfAbsent("operation", " ");
            switch (operands.get("operation")){
                //Addition
                case "%2B":
                    result=operandOne+operandTwo;
                    break;
                //Subtraction
                case "-":
                    result=operandOne-operandTwo;
                    break;
                //Multiplication
                case "*":
                    result=operandOne*operandTwo;
                    break;
                //Division
                case "%2F":
                    if(operandTwo!=0) {
                        result = operandOne / operandTwo;
                    }
                    break;
                //Remainder
                default:
                    if(operandTwo!=0) {
                        result = operandOne % operandTwo;
                    }
                    break;
            }

            jsonObject.put("result",result);
            jsonObject.put("expression",operands.get("leftOperand") + " "
                    + operands.get("operation") + " "
                    + operands.get("rightOperand"));

//            operands.forEach((key,val) -> jsonObject.put(key,val));


        //end your code
        String response = msgToClient + jsonObject.toString();
        //try{
            os.write(response.getBytes());
            os.flush();
            socket.close();
        }catch (IOException e){
            System.out.println("RequestProcessor.run IOException ");
            e.printStackTrace();
        }
    }
}
