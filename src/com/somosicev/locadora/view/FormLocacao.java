package com.somosicev.locadora.view;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.somosicev.locadora.data.ClienteRepository;
import com.somosicev.locadora.data.Conexao;
import com.somosicev.locadora.data.LocacaoRepository;
import com.somosicev.locadora.data.VeiculoRepository;
import com.somosicev.locadora.model.Locacao;

public class FormLocacao extends JFrame  {

    private final Connection conn;
    private final LocacaoRepository repoLocacao;
    private final ClienteRepository repoCliente;
    private final VeiculoRepository repoVeiculo;

    private JLabel lblTitulo;
    private DefaultTableModel model;
    private JTable tblLocacao;

    public FormLocacao() {
        this.conn = Conexao.getInstance();
        this.repoLocacao = new LocacaoRepository(conn);
        this.repoCliente = new ClienteRepository(conn);
        this.repoVeiculo = new VeiculoRepository(conn);
        inicializa();
        configuraEventos();
        refresh();
    }

    private void inicializa() {
        setTitle("Sistema de Gestão de Locadoras");
        setLayout(null);
        setBounds(0, 0, 580, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblTitulo = new JLabel("Locações de Veículos");

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Data");
        model.addColumn("Cliente");
        model.addColumn("Veículo");
        model.addColumn("Diárias");
        model.addColumn("Unitário");
        model.addColumn("Total");
        
        tblLocacao = new JTable(model);
        tblLocacao.getColumnModel().getColumn(0).setPreferredWidth(120);
        tblLocacao.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblLocacao.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblLocacao.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblLocacao.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblLocacao.getColumnModel().getColumn(5).setPreferredWidth(100);
        tblLocacao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollTable = new JScrollPane(tblLocacao);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblLocacao.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tblLocacao.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tblLocacao.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        lblTitulo.setBounds(20, 5, 300, 25);
        scrollTable.setBounds(20, 35, 540, 540);

        add(lblTitulo);
        add(scrollTable);

    }

    private void configuraEventos() {

    }

    private void refresh() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("###,##0.00");

        List<Locacao> lista = repoLocacao.findAll();
        model.setRowCount(0);
        for (Locacao locacao:lista) {
            model.addRow(new Object[]{ 
                sdf.format(locacao.getDataLocacao()), 
                locacao.getCliente().getNome(), 
                locacao.getVeiculo().getModelo(), 
                locacao.getQuantidadeDiarias(), 
                df.format(locacao.getValorDiaria()), 
                df.format(locacao.getValorDiaria().multiply(new BigDecimal(locacao.getQuantidadeDiarias())))
            });
        }
    }

}
