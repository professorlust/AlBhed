/*  
 *  Jay Sheridan (sheridjs@rose-hulman.edu)
 *  2/2/2002
 *
 *  This program translates english text into the fictional
 *  "AlBhed" language of the Squaresoft game Final Fantasy 10.
 */

import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class AlBhed extends Applet implements ActionListener
{

	public void init()
	{
		//setLayout( new FlowLayout() );
		//add( new GUI() );
		makeObjects();
		makeLayout();
		toAlBhed.addActionListener( this );
		toEnglish.addActionListener( this );
		
	}
	
	public AlBhed()
	{
		init();
	}

	public static void main(String[] args)
	{
		Frame f = new CloseableFrame();

		f.setLayout(new FlowLayout());
		f.setTitle("FF10 AlBhed Translator");
		f.add(new AlBhed());
		f.pack();
		f.show();
	}
	
	// Start defining the GUI
	private TextArea theTextBox;
	private Button toAlBhed;
	private Button toEnglish;
	private String dict_AlBhed;
	private String dict_Englsh;
	

	private void makeObjects()
	{
		String descr = "Text goes here!";
		theTextBox = new TextArea( descr, 10, 50 );
		toAlBhed = new Button( "To AlBhed" );
		toEnglish = new Button( "To English" );
		
		dict_Englsh = "abcdefghijklmnopqrstuvwxyz";	
		dict_AlBhed = "ypltavkrezgmshubxncdijfqow";
	}		
	
	private void makeLayout()
	{
		Panel textPanel = new Panel();
		Panel buttonPanel = new Panel();
		
		textPanel.setLayout( new FlowLayout() );
		textPanel.add( theTextBox );
		
		buttonPanel.setLayout( new FlowLayout() );
		buttonPanel.add( toAlBhed );
		buttonPanel.add( toEnglish );
		
		setLayout( new BorderLayout() );
		add( textPanel, "North" );
		add( buttonPanel, "South" );
	}
	
	//Action handlers
	public void actionPerformed(ActionEvent evt)
	{
		if( evt.getActionCommand() == "To AlBhed" )
		{ onAlBhedBtn(); }
		else if( evt.getActionCommand() == "To English" )
		{ onEnglishBtn(); }
		else {}
	}
	
	private void onAlBhedBtn()
	{
		String s = theTextBox.getText();
		s = translate(s, dict_Englsh, dict_AlBhed);
		theTextBox.setText( s );
	}
	
	private void onEnglishBtn()
	{
		String s = theTextBox.getText();
		s = translate(s, dict_AlBhed, dict_Englsh);
		theTextBox.setText( s );
	}
	
	private String translate( String theText, String dict1, String dict2)
	{
		if (dict1.length() != dict2.length())
			return "Error: Dictionaries not equal length.";
		
		StringBuffer newText = new StringBuffer(theText);
		Character c;
		char cval;
		int num;
		
		for (int i=0; i<newText.length(); i++) {
			c = new Character( newText.charAt(i) );
			cval = c.charValue();
			
			if (c.isLetter( cval )){
				if(c.isUpperCase( cval )){
					num = dict1.indexOf( c.toLowerCase(cval) );
					newText.setCharAt(i, c.toUpperCase(dict2.charAt(num)));
				} else {					
					num = dict1.indexOf( cval );
					newText.setCharAt(i, dict2.charAt(num));
				}
			}				
		}
		
		return newText.toString();
	}
}