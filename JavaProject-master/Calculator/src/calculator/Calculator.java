package calculator;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;

public class Calculator implements ActionListener{
 
    JFrame guiFrame;
    JPanel buttonPanel;
    JTextField numberCalc;
    int calcOperation = 0;
    int currentCalc;
    public static boolean nextPress = false;
    
    public static void main(String[] args) {
     
    	//Trying a solution
    	while(nextPress == false)
    	{
    	    try 
    	    {
    	       Thread.sleep(200);
    	    } catch(InterruptedException e) 
    	    {
    	    }
    	}
    	  
         //Use the event dispatch thread for Swing components
         EventQueue.invokeLater(new Runnable()
         {
             
            @Override
             public void run()
             {
                 
                 new Calculator();         
             }
         });
              
    }
    
    public Calculator()
    {
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("Simple Calculator");
        guiFrame.setSize(300,300);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        
        numberCalc = new JTextField();
        numberCalc.setHorizontalAlignment(JTextField.RIGHT);
        numberCalc.setEditable(false);
        
        guiFrame.add(numberCalc, BorderLayout.NORTH);
        
        buttonPanel = new JPanel();
               
        //Make a Grid that has three rows and four columns
        buttonPanel.setLayout(new GridLayout(4,4));   
        guiFrame.add(buttonPanel, BorderLayout.CENTER);
        
        //Add the number buttons
        for (int i=1;i<10;i++)
        {
            addButton(buttonPanel, String.valueOf(i));
        }

        JButton addButton = new JButton("+");
        addButton.setActionCommand("+");
        
        OperatorAction subAction = new OperatorAction(1);
        addButton.addActionListener(subAction);
        
        JButton subButton = new JButton("-");
        subButton.setActionCommand("-");
        
        OperatorAction addAction = new OperatorAction(2);
        subButton.addActionListener(addAction);
        
        JButton multiplyButton = new JButton("*");
        multiplyButton.setActionCommand("*");
        
        OperatorAction multiAction = new OperatorAction(3);
        multiplyButton.addActionListener(multiAction);
        
        	JButton divideButton = new JButton("/");
        	divideButton.setActionCommand("/");
        	
        	OperatorAction diviAction = new OperatorAction(4);
        	divideButton.addActionListener(diviAction);
        	
        	JButton clearButton = new JButton("ce");
        	clearButton.setActionCommand("ce");
        	
        	OperatorAction clearAction = new OperatorAction(5);
        	clearButton.addActionListener(clearAction);
        	
        	JButton blankButton = new JButton(" ");
        	
        JButton equalsButton = new JButton("=");
        equalsButton.setActionCommand("=");
        equalsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if (!numberCalc.getText().isEmpty())
                {
                    int number = Integer.parseInt(numberCalc.getText()); 
                    if (calcOperation == 1)
                    {
                        int calculate = currentCalc  + number;
                        numberCalc.setText(Integer.toString(calculate));
                    }
                    if (calcOperation == 2)
                    {
                        int calculate = currentCalc  - number;
                        numberCalc.setText(Integer.toString(calculate));
                    }
                    
                    if (calcOperation == 3)
                    {
                    		int calculate = currentCalc * number;
                    		numberCalc.setText(Integer.toString(calculate));
                    }
                    
                    if (calcOperation == 4)
                    {
                    		int calculate = currentCalc / number;
                    		numberCalc.setText(Integer.toString(calculate));
                    }
                    
                    if (calcOperation == 5)
                    {
                    	 	int calculate = 0;
                    	 	numberCalc.setText(Integer.toString(calculate));
                    }
                }
            }
        });
        
        buttonPanel.add(addButton);
        buttonPanel.add(subButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(divideButton);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(blankButton);
        guiFrame.setVisible(true);  
    }
    
    //All the buttons are following the same pattern
    //so create them all in one place.
    private void addButton(Container parent, String name)
    {
        JButton but = new JButton(name);
        but.setActionCommand(name);
        but.addActionListener(this);
        parent.add(but);
    }
    
    //As all the buttons are doing the same thing it's
    //easier to make the class implement the ActionListener
    //interface and control the button clicks from one place
    @Override
    public void actionPerformed(ActionEvent event)
    {
    	
        //get the Action Command text from the button
        String action = event.getActionCommand();
    	//This is the help from stack overflow
    	currentCalc = Integer.parseInt(numberCalc.getText());
        
        //set the text using the Action Command text
       
        numberCalc.setText(numberCalc.getText() + action);       
        
    }
    
    private class OperatorAction implements ActionListener
    {
        private int operator;
        
        public OperatorAction(int operation)
        {
            operator = operation;
        }
        
        public void actionPerformed(ActionEvent event)
        {
            currentCalc = Integer.parseInt(numberCalc.getText()); 
            calcOperation = operator;
        }
    }
}