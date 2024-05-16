//  package br.unitins.topicos1.model;

//  import java.time.LocalDateTime;
//  import java.util.List;

//  import jakarta.persistence.CascadeType;
//  import jakarta.persistence.Entity;
//  import jakarta.persistence.JoinColumn;
//  import jakarta.persistence.ManyToOne;
//  import jakarta.persistence.OneToMany;

//  @Entity
//  public class Venda extends DefaultEntity {

//      private LocalDateTime dataHora;

//      @ManyToOne
//      @JoinColumn(name = "id_usuario")
//      private Usuario usuario;

//      @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "venda")
//      private List<ItemVenda> itens;

//      private TipoPagamento tipoPagamento;

//      private Double valorTotal;

//      private StatusVenda statusVenda;

//      public LocalDateTime getDataHora() {
//          return dataHora;
//      }

//      public void setDataHora(LocalDateTime dataHora) {
//          this.dataHora = dataHora;
//      }

//      public Usuario getUsuario() {
//          return usuario;
//      }

//      public void setUsuario(Usuario usuario) {
//          this.usuario = usuario;
//      }

//      public List<ItemVenda> getItens() {
//          return itens;
//      }

//      public void setItens(List<ItemVenda> itens) {
//          this.itens = itens;
//      }

//      public TipoPagamento getTipoPagamento() {
//          return tipoPagamento;
//      }

//      public void setTipoPagamento(TipoPagamento tipoPagamento) {
//          this.tipoPagamento = tipoPagamento;
//      }

//      public Double getValorTotal() {
//          return valorTotal;
//      }

//      public void setValorTotal(Double valorTotal) {
//          this.valorTotal = valorTotal;
//      }

//      public StatusVenda getStatusVenda() {
//          return statusVenda;
//      }

//      public void setStatusVenda(StatusVenda statusVenda) {
//          this.statusVenda = statusVenda;
//      }

//  }
