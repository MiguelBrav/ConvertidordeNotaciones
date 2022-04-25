/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertidornotaciones;

import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Expresion {
  private String InfixExp;//Infix Expression
    private String PostfixExp;//Postfix Expression
    private String PrefixExpo;//Prefix Expression
    private int LengthExp;//Length of Expression
    
    Expresion(){
        LengthExp = 0;
        InfixExp=new String();
        PrefixExpo=new String();
        PrefixExpo=new String();
                
    }
 
 // Get Functions
 // Begin
    public int getLengthExp(){
        return LengthExp;//Return Length of Expression
    }

    public String getInfix(){
        return InfixExp;//Return Infix Expression
    }
    
    public String getPrefix(){
        return PrefixExpo;//Return Prefix Expression
    }
    
    public String getPostfix(){
        return PostfixExp;//Return Postfix Expression
    }

// End
    
// Set Functions
// Begin
    
    public void setInfix(String Input,int length){
        InfixExp = Input;
        LengthExp = length;
    }
    
    public void setPostfix(String Input){
        PostfixExp = Input;
    }
    
    public void setPrefix(String Input){
        PrefixExpo = Input;
    }
    
  
    
// End      
    
    private Elementopila ReadTokens(int start,String input){//Read 1 token frome Input String
        
        Elementopila result=new Elementopila();

        try{
            while(input.charAt(start) == ' '){
                start++; 
            }
        
            //if the index is end of string we can't check next index
            //becouse it's outof bound
            //begin
        if(start == input.length()-1 && (input.charAt(start)==')')){
            result.Str = input.substring(start);
            result.priorty = 5;
            result.end = start+1;
        } 
        
        else if(start == input.length()-1 && (input.charAt(start) >= '0' && input.charAt(start) <= '9')){
            result.Str = input.substring(start);
            result.priorty = 0;
            result.end = ++start;
        }
        
        else if(input.substring(start,start+1).equals("(")||input.substring(start,start+1).equals(")")){
            result.Str = input.substring(start,start+1);
            result.priorty = 5;
            result.end = start+1;
        }
        
        else if('0'<=input.substring(start,start+1).charAt(0) && 
                input.substring(start,start+1).charAt(0) <='9'){
            String number=new String();
            do{
                number=number.concat(input.substring(start,start+1));
                start++;
                if(start == input.length()-1){
                    if((input.charAt(start) >= '0' && input.charAt(start) <= '9')){
                        number=number.concat(input.substring(start));
                        start++;    
                    }
                    break;   
                }
            }
            while(('0' <= input.substring(start,start+1).charAt(0)) &&
                    (input.substring(start,start+1).charAt(0) <= '9' && start < input.length()-1)
                    || (input.substring(start,start+1).charAt(0)) == '.' );
            result.Str = number;
            result.priorty = 1;
            result.end = start;
        }                    
        else if(input.substring(start,start+1).equals("^")||input.substring(start,start+1).equals("*")
                ||(input.substring(start,start+1).equals("/"))||
                (input.substring(start,start+1).equals("%"))){
            
            result.Str = input.substring(start,start+1);
            result.priorty = 3;
            result.end = start+1;
        }
        
        else if(input.substring(start,start+1).equals("+")||input.substring(start,start+1).equals("-")){
            result.Str = input.substring(start,start+1);
            result.priorty = 2;
            result.end = start+1;           
        }
        
        else if(input.substring(start,start+3).equals("sin")||input.substring(start,start+3).equals("cos")
                ||(input.substring(start,start+3).equals("tan"))||(input.substring(start,start+3).equals("cot"))
                ||(input.substring(start,start+4).equals("sqrt"))){
                    result.Str = input.substring(start,start+3);
                    result.priorty = 4;
                    result.end = start+3;  
                }  
        else
            throw new ArithmeticException("error");
        }
        catch(StringIndexOutOfBoundsException | ArithmeticException exp1){
            JOptionPane.showMessageDialog(null,"invalid infix expression!"+"\n"+exp1.getLocalizedMessage());
        }
        return result;
    }
    
    public String convert(String input){//Convert Input String to postfix form
        Pila myStack=new Pila();
        int s=0;//start of infix expression
        String output=new String();
        Elementopila temp;
        try{
            while(s < input.length()){
                temp = ReadTokens(s,input);
                if(temp.Str.charAt(0) >= '0' && temp.Str.charAt(0) <= '9'){
                 output=output.concat(temp.Str); 
                 output=output.concat(" "); 
                }
                else if(!temp.Str.equals(")"))
                {
                    if(myStack.isEmpty())
                        myStack.push(temp);
                    else{
                        while(!myStack.isEmpty())
                        {
                            if(myStack.top().priorty >= temp.priorty &&
                                !((myStack.top().Str.equals(myStack.top().Str))
                                &&(myStack.top().Str.equals("^")))
                                &&!(myStack.top().Str.equals("(")))
                            {
                                output=output.concat(myStack.pop().Str);
                                output=output.concat(" ");
                            }
                            else
                                break;
                        }
                        myStack.push(temp);
                    }
                }
                else if(temp.Str.equals(")")){
                    String temp1;
                    do{
                        temp1=myStack.top().Str;
                        if(temp1.equals("(")){
                            myStack.pop();
                            break;
                        }
                        output=output.concat(myStack.pop().Str);
                        output=output.concat(" "); 
                    }while(true);
                }
               s=temp.end;
            }
            while(!myStack.isEmpty())
            {
                if(!(myStack.top().Str.equals("("))){
                    output=output.concat(myStack.pop().Str);
                    output=output.concat(" ");  
                }
                else
                    myStack.pop();
            }
            if(output.length()>0)
                output=output.substring(0,output.length()-1);
            return output;
        }
        catch(ArrayIndexOutOfBoundsException exp1){
            JOptionPane.showMessageDialog(null, exp1);
            return null;
        }
        catch(ArithmeticException exp2){
        JOptionPane.showMessageDialog(null, "Invalid Infix expression");
        return null;
    }
    }
    
    public String ReverseExpression(String input){//Reverse input Expression
        int s=0;
        String TempString=new String();
        Elementopila TempElement;
        Pila TempStack=new Pila();
        while(s < input.length()){
            TempElement = ReadTokens(s,input);
            if(TempElement.Str.equals("("))
                TempElement.Str=")";
            else if(TempElement.Str.equals(")"))
                TempElement.Str="(";
            TempStack.push(TempElement);
            s=TempElement.end;
        }
        while(!TempStack.isEmpty()){
         TempString=TempString.concat(TempStack.pop().Str);
         TempString=TempString.concat(" ");
        } 
        if(TempString.length()>0)
            TempString=TempString.substring(0, TempString.length()-1);
        return TempString; 
    }
    
      public String PosfixtoInfix(String input) {
        int s=0;
        String TempString=new String();
        Elementopila TempElement;
        
        
        
        
        return TempString; 
 
 
   
    }
    
 

    private int FunctionNumber(Elementopila SE){
        try{
            switch(SE.Str){
                case "sin":
                    return 1;
                case "cos":
                    return 2;
                case "tan":
                    return 3;
                case "cot":
                    return 4;
                case "sqr":
                    return 5;
                case "^":
                    return 6;
                case "%":
                    return 7;
                case "*":
                    return 8;
                case "/":
                    return 9;
                case "+":
                    return 10;
                case "-":
                    return 11;
                default:{
                    throw new ArithmeticException("Can't Calculate the Expression");
                }
            }    
        }
        catch(ArithmeticException exp){
            JOptionPane.showMessageDialog(null, exp);
            return -1;
        }
    }


  
    
}
