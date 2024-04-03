package br.unitins.topicos1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Armacao extends DefaultEntity {

    @Column(length = 15)
    private String referencia;

    @Column(length = 10)
    private String cor;

    @Column(length = 10)
    private String tamanho;

    @Column
    private Double precoCusto;

    @Column
    private Double precoVenda;

    // @Column(columnDefinition = "INT CHECK (estoque >= 0)")
    private Integer quantidade;

    private String nomeImagem;

    @ManyToOne
    @JoinColumn(name = "id_fabricante")
    private Fabricante fabricante;

    @Column(name = "material_armacao")
    private MaterialArmacao materialArmacao;

    @Column(name = "tipo_aro_armacao")
    private TipoAroArmacao tipoAroArmacao;

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(Double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public MaterialArmacao getMaterialArmacao() {
        return materialArmacao;
    }

    public void setMaterialArmacao(MaterialArmacao materialArmacao) {
        this.materialArmacao = materialArmacao;
    }

    public TipoAroArmacao getTipoAroArmacao() {
        return tipoAroArmacao;
    }

    public void setTipoAroArmacao(TipoAroArmacao tipoAroArmacao) {
        this.tipoAroArmacao = tipoAroArmacao;
    }

}
