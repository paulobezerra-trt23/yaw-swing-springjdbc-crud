package br.com.yaw.ssjc.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import br.com.yaw.ssjc.action.AbstractAction;
import br.com.yaw.ssjc.model.Mercadoria;

/**
 * Tela principal da aplicação. Apresenta uma lista com as mercadorias cadastradas. 
 * 
 * <p>A partir dessa tela eh possivel criar/editar ou pesquisar mercadoria.</p>
 * 
 * @author YaW Tecnologia
 */
public class ListaMercadoriasFrame extends JFrame {
	
	private MercadoriaTable tabela;
	private JScrollPane scrollPane;
	private JButton bNewMercadoria;
	private JButton bFindMercadoria;
	private JButton bRefreshLista;
	private JMenuBar menubar;
	private JMenu menuAjuda;
	private JMenuItem menuSobre;
	
	public ListaMercadoriasFrame() {
		setTitle("Lista de Mercadoria");
		
		inicializaComponentes();
		adicionaComponentes();
		
		pack();
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void inicializaComponentes() {
		tabela = new MercadoriaTable();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tabela);

		bNewMercadoria = new JButton("Nova");
		bNewMercadoria.setActionCommand("novaMercadoriaAction");
		bNewMercadoria.setMnemonic(KeyEvent.VK_N);
		
		bFindMercadoria = new JButton("Buscar");
		bFindMercadoria.setActionCommand("buscarMercadoriasAction");
		bFindMercadoria.setMnemonic(KeyEvent.VK_B);
		
		bRefreshLista = new JButton("Atualizar");
		bRefreshLista.setActionCommand("atualizarMercadoriasAction");
		bRefreshLista.setMnemonic(KeyEvent.VK_A);
		
		menubar = new JMenuBar();
		menuAjuda = new JMenu("Ajuda");
		menuAjuda.setMnemonic(KeyEvent.VK_J);
        menuSobre = new JMenuItem("Sobre");
        
        menuAjuda.add(menuSobre);
        menubar.add(menuAjuda);
        setJMenuBar(menubar);
	}
	
	private void adicionaComponentes(){
		add(scrollPane);
		JPanel panel = new JPanel();
		panel.add(bNewMercadoria);
		panel.add(bFindMercadoria);
		panel.add(bRefreshLista);
		add(panel, BorderLayout.SOUTH);
	}
	
	public JButton getNewButton() {
		return bNewMercadoria;
	}

	public JButton getRefreshButton() {
		return bRefreshLista;
	}
	
	public JButton getFindButton() {
		return bFindMercadoria;
	}
	
	public void refreshTable(List<Mercadoria> mercadorias) {
		tabela.reload(mercadorias);
	}
	
	public Mercadoria getSelectedMercadoria() {
		return tabela.getMercadoriaSelected();
	}
	
	public MercadoriaTable getTable() {
		return tabela;
	}
	
	public JMenuItem getMenuSobre() {
		return menuSobre;
	}
	
	public void addActionF1(final AbstractAction action) {
		menuAjuda.getInputMap().put(KeyStroke.getKeyStroke("F1"),"pressed");
        menuAjuda.getActionMap().put("pressed", new javax.swing.AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				action.actionPerformed();
			}
		});
	}
}
