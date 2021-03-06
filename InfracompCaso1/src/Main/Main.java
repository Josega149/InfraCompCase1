package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

import PackageClientes.ClienteManager;
import PackageServidor.ServidorBuffer;

public class Main 
{
	public Main()
	{
		leerDatos();
		
	}
	public void leerDatos()
	{
		JFileChooser chooser = new JFileChooser("./data");
		chooser.setDialogTitle("Abrir archivo con los datos");

		File datos = null;
		int resultado = chooser.showOpenDialog(null);

		if(resultado == JFileChooser.APPROVE_OPTION)
		{
			datos = chooser.getSelectedFile();
			try
			{
				BufferedReader br = new BufferedReader(new FileReader(datos));
				String numServidoresS = br.readLine().split(":")[1];
				int numServidores = Integer.parseInt(numServidoresS);
				
				String tamanoBufferS = br.readLine().split(":")[1];
				int numMaxClientesPosibles = Integer.parseInt(tamanoBufferS);
				
				String tamanoClientes = br.readLine().split(":")[1];
				int numClientes = Integer.parseInt(tamanoClientes);
				
				int [] mensajesPorCliente = new int [numClientes];
				String listaString= "";
				for(int i=0; i< mensajesPorCliente.length;i++)
				{
					int linea = Integer.parseInt(br.readLine().split(":")[1]);
					mensajesPorCliente[i] = linea;
					listaString += " "+linea;
				}
				System.out.println("num clientes" + numClientes);
				System.out.println("num servidores" + numServidores);
				System.out.println("num cola " + numMaxClientesPosibles);
				System.out.println("mensajes por cliente" + listaString);
				br.close();
				ServidorBuffer server = new ServidorBuffer(numServidores, numMaxClientesPosibles, numClientes);
				ClienteManager clientes = new ClienteManager(numClientes, mensajesPorCliente, server);
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) 
	{
		Main main = new Main();
	}

}
