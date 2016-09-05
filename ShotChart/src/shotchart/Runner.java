package shotchart;

import javax.swing.SwingUtilities;

public class Runner {

	public static void main(String[] args) throws Exception {
		
		Data d = new Data();
    	d.getData("201599");//enter player id
    	d.convert();
    	d.addToArrayList();
    	
    	SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MakeGraph().setVisible(true);
			}
		});
	}
}