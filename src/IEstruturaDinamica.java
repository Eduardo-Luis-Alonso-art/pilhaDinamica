public interface IEstruturaDinamica {

    void inserirElemento(Object elemento);
    void inserirSequencia(Object[] elementos);
    boolean removerElemento(Object elemento);
    void removerSequencia(Object[] elementos);
    void removerTodasOcorrencias(Object elemento);
    boolean estaVazia();
    boolean buscarElemento(Object elemento);
    void ordenarCrescente();
    void ordenarDecrescente();
    int quantidadeElementos();
    void editarElemento(Object elementoAntigo, Object elementoNovo);
    void limpar();
    void exibir();
    No obterPrimeiroElemento();
    No obterUltimoElemento();
}