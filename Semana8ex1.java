import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Semana8ex1 {
    private Arvore arvore = new Arvore();
    private JTextArea textArea;

    public Semana8ex1() {
        JFrame frame = new JFrame("Árvore AVL");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton insertButton = new JButton("Inserir");
        JButton searchButton = new JButton("Buscar");
        JButton removeButton = new JButton("Remover");
        JButton inOrderButton = new JButton("InOrdem");
        JButton preOrderButton = new JButton("PreOrdem");
        JButton postOrderButton = new JButton("PosOrdem");

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Digite o número total de ocorrências:");
                if (input != null) {
                    try {
                        int totalOcorrencias = Integer.parseInt(input);
                        for (int i = 0; i < totalOcorrencias; i++) {
                            int elemento = new Random().nextInt(10000) + 1;
                            arvore.raiz = arvore.inserir(arvore.raiz, elemento);
                        }
                        updateTextArea();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Número inválido. Por favor, insira um número válido.");
                    }
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Digite o número a ser buscado:");
                if (input != null) {
                    try {
                        int numero = Integer.parseInt(input);
                        Node result = arvore.buscar(arvore.raiz, numero);
                        if (result != null) {
                            JOptionPane.showMessageDialog(null, "Número encontrado na árvore.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Número não encontrado na árvore.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Número inválido. Por favor, insira um número válido.");
                    }
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Digite o número a ser removido:");
                if (input != null) {
                    try {
                        int numero = Integer.parseInt(input);
                        arvore.raiz = arvore.remover(arvore.raiz, numero);
                        updateTextArea();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Número inválido. Por favor, insira um número válido.");
                    }
                }
            }
        });

        inOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("Travessia em InOrdem:\n");
                arvore.inOrdem(arvore.raiz);
            }
        });

        preOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("Travessia em PreOrdem:\n");
                arvore.preOrdem(arvore.raiz);
            }
        });

        postOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("Travessia em PosOrdem:\n");
                arvore.posOrdem(arvore.raiz);
            }
        });

        buttonPanel.add(insertButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(inOrderButton);
        buttonPanel.add(preOrderButton);
        buttonPanel.add(postOrderButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void updateTextArea() {
        textArea.setText("Árvore AVL:\n");
        displayTree(arvore.raiz, 0, "", true);
    }

    private void displayTree(Node node, int level, String prefix, boolean isLeft) {
        if (node != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < level; i++) {
                sb.append("  ");
            }
            sb.append(prefix);
            sb.append(node.getInformacao());
            textArea.append(sb.toString() + "\n");

            if (isLeft) {
                displayTree(node.getEsquerda(), level + 1, "L: ", true);
                displayTree(node.getDireita(), level + 1, "R: ", false);
            } else {
                displayTree(node.getEsquerda(), level + 1, "L: ", true);
                displayTree(node.getDireita(), level + 1, "R: ", false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Semana8ex1();
            }
        });
    }
}
