public class Arvore {
    public Node raiz;

    public Arvore() {
        raiz = null;
    }

    public void inOrdem(Node raiz) {
        if (raiz != null) {
            inOrdem(raiz.getEsquerda());
            System.out.println(raiz.getInformacao());
            inOrdem(raiz.getDireita());
        }
    }

    public void preOrdem(Node raiz) {
        if (raiz != null) {
            System.out.println(raiz.getInformacao());
            preOrdem(raiz.getEsquerda());
            preOrdem(raiz.getDireita());
        }
    }

    public void posOrdem(Node raiz) {
        if (raiz != null) {
            posOrdem(raiz.getEsquerda());
            posOrdem(raiz.getDireita());
            System.out.println(raiz.getInformacao());
        }
    }

    public int altura(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(altura(node.getEsquerda()), altura(node.getDireita())) + 1;
    }

    private int balanceamento(Node node) {
        if (node == null) {
            return 0;
        }
        return altura(node.getEsquerda()) - altura(node.getDireita());
    }

    private Node rotacaoEsquerda(Node raiz) {
        Node novaRaiz = raiz.getDireita();
        Node temp = novaRaiz.getEsquerda();
        novaRaiz.setEsquerda(raiz);
        raiz.setDireita(temp);
        return novaRaiz;
    }

    private Node rotacaoDireita(Node raiz) {
        Node novaRaiz = raiz.getEsquerda();
        Node temp = novaRaiz.getDireita();
        novaRaiz.setDireita(raiz);
        raiz.setEsquerda(temp);
        return novaRaiz;
    }

    public Node inserir(Node atual, int numero) {
        if (atual == null) {
            Node no = new Node();
            no.setInformacao(numero);
            return no;
        }
        if (numero >= atual.getInformacao()) {
            atual.setDireita(inserir(atual.getDireita(), numero));
        } else {
            atual.setEsquerda(inserir(atual.getEsquerda(), numero));
        }

        int balanceamento = balanceamento(atual);

        if (balanceamento > 1) {
            if (numero < atual.getEsquerda().getInformacao()) {
                // Rotação direita simples
                return rotacaoDireita(atual);
            } else {
                // Rotação esquerda-direita (dupla)
                atual.setEsquerda(rotacaoEsquerda(atual.getEsquerda()));
                return rotacaoDireita(atual);
            }
        }

        if (balanceamento < -1) {
            if (numero >= atual.getDireita().getInformacao()) {
                // Rotação esquerda simples
                return rotacaoEsquerda(atual);
            } else {
                // Rotação direita-esquerda (dupla)
                atual.setDireita(rotacaoDireita(atual.getDireita()));
                return rotacaoEsquerda(atual);
            }
        }

        return atual;
    }

    public Node buscar(Node raiz, int valor) {
        if (raiz == null || raiz.getInformacao() == valor) {
            return raiz;
        }

        if (valor < raiz.getInformacao()) {
            return buscar(raiz.getEsquerda(), valor);
        }

        return buscar(raiz.getDireita(), valor);
    }

    public Node remover(Node raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.getInformacao()) {
            raiz.setEsquerda(remover(raiz.getEsquerda(), valor));
        } else if (valor > raiz.getInformacao()) {
            raiz.setDireita(remover(raiz.getDireita(), valor));
        } else {
            if (raiz.getEsquerda() == null || raiz.getDireita() == null) {
                Node temp = null;
                if (temp == raiz.getEsquerda()) {
                    temp = raiz.getDireita();
                } else {
                    temp = raiz.getEsquerda();
                }

                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else {
                    raiz = temp;
                }
            } else {
                Node temp = minValueNode(raiz.getDireita());
                raiz.setInformacao(temp.getInformacao());
                raiz.setDireita(remover(raiz.getDireita(), temp.getInformacao()));
            }
        }

        if (raiz == null) {
            return raiz;
        }

        int balanceamento = balanceamento(raiz);

        if (balanceamento > 1) {
            if (balanceamento(raiz.getEsquerda()) >= 0) {
                return rotacaoDireita(raiz);
            } else {
                raiz.setEsquerda(rotacaoEsquerda(raiz.getEsquerda()));
                return rotacaoDireita(raiz);
            }
        }

        if (balanceamento < -1) {
            if (balanceamento(raiz.getDireita()) <= 0) {
                return rotacaoEsquerda(raiz);
            } else {
                raiz.setDireita(rotacaoDireita(raiz.getDireita()));
                return rotacaoEsquerda(raiz);
            }
        }

        return raiz;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.getEsquerda() != null) {
            current = current.getEsquerda();
        }
        return current;
    }
}
