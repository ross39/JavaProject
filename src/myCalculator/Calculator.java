package myCalculator;
//This is a calculator app developed for 2nd year java @ GMIT


import java.awt.*;  
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
 

  
public class Calculator extends Frame  
{  
  
public boolean setClear=true;  
double number, memValue;  
char op;  
  
//Used arrays as it was easier
//idea from stack overflow
String DigitalButtons[] = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", "+/-", "." };  
String operatorButtons[] = {"/", "sqrt", "*", "%", "-", "1/X", "+", "=" };  
String memoryButtons[] = {"MC", "MR", "MS", "M+" };  
String specialButtons[] = {"Backspc", "C", "CE" };  
  
Digits digitButton[]=new Digits[DigitalButtons.length];  
Operation operatorButton[]=new Operation[operatorButtons.length];  
Memory memoryButton[]=new Memory[memoryButtons.length];  
Special specialButton[]=new Special[specialButtons.length];  
  
final int FRAME_WIDTH=456,FRAME_HEIGHT=325;  
final int HEIGHT=30, WIDTH=30, HEIGHT_SPACE=10,VERTICALSPACE=10;  
final int WidthMargin=50, HeightMargin=50;  

Label displayLabel=new Label("0",Label.RIGHT);  
Label memLabel=new Label(" ",Label.RIGHT);  
  


//calculator class
Calculator(String frameText)//constructor  
{  
super(frameText);  
 
//use variables as it's final
int X=WidthMargin, Y=HeightMargin;  
displayLabel.setBounds(X,Y,240,HEIGHT);  
displayLabel.setBackground(new Color(16, 46, 94));  
displayLabel.setForeground(new Color(132, 83, 62));

add(displayLabel);  
  
memLabel.setBounds(WidthMargin,  HeightMargin+HEIGHT+ VERTICALSPACE,WIDTH, HEIGHT);  
add(memLabel);  
  
// set Co-ordinates for Memory Buttons  
X=WidthMargin;   
Y=HeightMargin+2*(HEIGHT+VERTICALSPACE);  
for(int i=0; i<memoryButton.length; i++)  
{  
memoryButton[i]=new Memory(X,Y,WIDTH,HEIGHT,memoryButtons[i], this);  
memoryButton[i].setForeground(new Color(56, 130, 170));  
Y+=HEIGHT+VERTICALSPACE;  
}  
  
//set Co-ordinates for Special Buttons  
X=WidthMargin+1*(WIDTH+HEIGHT_SPACE); Y=HeightMargin+1*(HEIGHT+VERTICALSPACE);  
for(int i=0;i<specialButton.length;i++)  
{  
specialButton[i]=new Special(X,Y,WIDTH*2,HEIGHT,specialButtons[i], this);  
specialButton[i].setForeground(new Color(56, 130, 170));  
X=X+2*WIDTH+HEIGHT_SPACE;  
}  
  
//set Co-ordinates for Digit Buttons  
int digitX=WidthMargin+WIDTH+HEIGHT_SPACE;  
int digitY=HeightMargin+2*(HEIGHT+VERTICALSPACE);  
X=digitX;  Y=digitY;  
for(int i=0;i<digitButton.length;i++)  
{  
digitButton[i]=new Digits(X,Y,WIDTH,HEIGHT,DigitalButtons[i], this);  
digitButton[i].setForeground(Color.BLACK);  
X+=WIDTH+HEIGHT_SPACE;  
if((i+1)%3==0){X=digitX; Y+=HEIGHT+VERTICALSPACE;}  
}  
  
//set Co-ordinates for Operator Buttons  
int opsX=digitX+2*(WIDTH+HEIGHT_SPACE)+HEIGHT_SPACE;  
int opsY=digitY;  
X=opsX;  Y=opsY;  
for(int i=0;i<operatorButton.length;i++)  
{  
X+=WIDTH+HEIGHT_SPACE;  
operatorButton[i]=new Operation(X,Y,WIDTH,HEIGHT,operatorButtons[i], this);  
operatorButton[i].setForeground(Color.BLACK);  
if((i+1)%2==0){X=opsX; Y+=HEIGHT+VERTICALSPACE;}  
}  
  
//THIS DEALS WITH WHAT HAPPENS WHEN THE WINDOW CLOSES
addWindowListener(new WindowAdapter()  
{  
public void windowClosing(WindowEvent ev)  
{	
	//prompts user to confirm if they wish to leave or not
	//used Jframe as frame was giving out weird behaivour
	int dialogButton = 0;
	int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you wish to exit","Warning",dialogButton);
	if(dialogResult == JOptionPane.YES_OPTION){
	  // Saving code here
		System.exit(0); 
	}
	
}//end window closing
	    
});  
  
setLayout(null);  
setSize(FRAME_WIDTH,FRAME_HEIGHT);  
setVisible(true);  
}//end calculator class
//////////////////////////////////  
static String getFormattedText(double temp)  
{  
String resText=""+temp;  
if(resText.lastIndexOf(".0")>0)  
    resText=resText.substring(0,resText.length()-2);  
return resText;  
}  

//Main method 
public static void main(String []args)  
{  
new Calculator("Welcome to my java project calculator");  
}  
}  
  
//class digits deals with the digits on the calculator
  
class Digits extends Button implements ActionListener  
{  
Calculator cl; //current instance of calculator 
  
//////////////////////////////////////////  
Digits(int x,int y, int width,int height,String cap, Calculator clc)  
{  
super(cap);  
setBounds(x,y,width,height);  
this.cl=clc;  
this.cl.add(this);  
addActionListener(this);  
}  
////////////////////////////////////////////////  
static boolean isInString(String s, char ch)  
{  
for(int i=0; i<s.length();i++) if(s.charAt(i)==ch) return true;  
return false;  
}  
/////////////////////////////////////////////////  
public void actionPerformed(ActionEvent ev)  
{  
String tempText=((Digits)ev.getSource()).getLabel();  
  
if(tempText.equals("."))  
{  
 if(cl.setClear)   
    {cl.displayLabel.setText("0.");cl.setClear=false;}  
 else if(!isInString(cl.displayLabel.getText(),'.'))  
    cl.displayLabel.setText(cl.displayLabel.getText()+".");  
 return;  
}  
  
int index=0;  
try{  
        index=Integer.parseInt(tempText);  
    }catch(NumberFormatException e){return;}  
  
if (index==0 && cl.displayLabel.getText().equals("0")) return;  
  
if(cl.setClear)  
            {cl.displayLabel.setText(""+index);cl.setClear=false;}  
else  
    cl.displayLabel.setText(cl.displayLabel.getText()+index);  
}//actionPerformed  
}//class defination  
  
//class Operation performes the operations 
  
class Operation extends Button implements ActionListener  
{  
Calculator cl;  
  
Operation(int x,int y, int width,int height,String cap, Calculator clc)  
{  
super(cap);  
setBounds(x,y,width,height);  
this.cl=clc;  
this.cl.add(this);  
addActionListener(this);  
}  

//This code deals with actions performed
public void actionPerformed(ActionEvent ev)  
{  
String opText=((Operation)ev.getSource()).getLabel();  
  
cl.setClear=true;  
double temp=Double.parseDouble(cl.displayLabel.getText());  
  
if(opText.equals("1/x"))  
    {  
    try  
        {double tempd=1/(double)temp;  
        cl.displayLabel.setText(Calculator.getFormattedText(tempd));}  
    catch(ArithmeticException excp)  
                        {cl.displayLabel.setText("Divide by 0.");}  
    return;  
    }  
if(opText.equals("sqrt"))  
    {  
    try  
        {double tempd=Math.sqrt(temp);  
        cl.displayLabel.setText(Calculator.getFormattedText(tempd));}  
            catch(ArithmeticException excp)  
                    {cl.displayLabel.setText("Divide by 0.");}  
    return;  
    }  
if(!opText.equals("="))  
    {  
    cl.number=temp;  
    cl.op=opText.charAt(0);  
    return;  
    }  
// process = button pressed  
//Include a try and catch block in case the user tries to divide by 0
switch(cl.op)  
{  
case '+':  
    temp+=cl.number;break;  
case '-':  
    temp=cl.number-temp;break;  
case '*':  
    temp*=cl.number;break;  
case '%':  
    try{temp=cl.number%temp;}  
    catch(ArithmeticException excp)  
        {cl.displayLabel.setText("Divide by 0."); return;}  
    break;  
case '/':  
    try{temp=cl.number/temp;}  
        catch(ArithmeticException excp)  
                {cl.displayLabel.setText("Divide by 0."); return;}  
    break;  
}//switch  
  
cl.displayLabel.setText(Calculator.getFormattedText(temp));  
//cl.number=temp;  
}//actionPerformed  
}//class  
  
 
  
class Memory extends Button implements ActionListener  
{  
Calculator cl;  
  
/////////////////////////////////  
Memory(int x,int y, int width,int height,String cap, Calculator clc)  
{  
super(cap);  
setBounds(x,y,width,height);  
this.cl=clc;  
this.cl.add(this);  
addActionListener(this);  
}  
 
public void actionPerformed(ActionEvent ev)  
{  
char memop=((Memory)ev.getSource()).getLabel().charAt(1);  
  
cl.setClear=true;  
double temp=Double.parseDouble(cl.displayLabel.getText());  
  
switch(memop)  
{  
case 'C':   
    cl.memLabel.setText(" ");cl.memValue=0.0;
    break;  
case 'R':   
    cl.displayLabel.setText(Calculator.getFormattedText(cl.memValue));
    break;  
case 'S':  
    cl.memValue=0.0;  
case '+':   
    cl.memValue+=Double.parseDouble(cl.displayLabel.getText());  
    if(cl.displayLabel.getText().equals("0") || cl.displayLabel.getText().equals("0.0")  )  
        cl.memLabel.setText(" ");  
    else   
        cl.memLabel.setText("M");     
    break;  
}//switch  
}//actionPerformed  
}//class  
  
//Special is a class that deals with the special buttons
  
class Special extends Button implements ActionListener  
{  
Calculator cl;  
  
Special(int x,int y, int width,int height,String cap, Calculator clc)  
{  
super(cap);  
setBounds(x,y,width,height);  
this.cl=clc;  
this.cl.add(this);  
addActionListener(this);  
}  

static String backSpace(String s)  
{  
String Res="";  
for(int i=0; i<s.length()-1; i++) Res+=s.charAt(i);  
return Res;  
}  
  
//action performed method
//checks for buttons and does action
public void actionPerformed(ActionEvent ev)  
{  
String opText=((Special)ev.getSource()).getLabel();  
//check for backspace button  
if(opText.equals("Backspc"))  
{  
String tempText=backSpace(cl.displayLabel.getText());  
if(tempText.equals(""))   
    cl.displayLabel.setText("0");  
else   
    cl.displayLabel.setText(tempText);  
return;  
}  
//check for "C" button and if present then reset
if(opText.equals("C"))   
{  
cl.number=0.0; cl.op=' '; cl.memValue=0.0;  
cl.memLabel.setText(" ");  
}  
  
//it must be CE button pressed  
cl.displayLabel.setText("0");cl.setClear=true;  
}//actionPerformed  
}//class  
  