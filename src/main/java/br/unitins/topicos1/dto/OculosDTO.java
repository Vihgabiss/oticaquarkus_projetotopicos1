package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class OculosDTO {
        @NotBlank(message = "O campo referência não pode ser nulo.")
        private final String referencia;
        @NotBlank(message = "O campo nome não pode ser nulo.")
        @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Cor inválida.")
        private final String cor;

        @NotBlank(message = "O campo tamanho não pode ser nulo.")
        private final String tamanho;

        @NotNull(message = "O precoCusto tamanho não pode ser nulo.")
        @Positive(message = "O valor deve ser positivo")
        private final Double precoCusto;

        @NotNull(message = "O campo precoVenda não pode ser nulo.")
        @Positive(message = "O valor deve ser positivo")
        private final Double precoVenda;

        @PositiveOrZero(message = "O valor deve ser positivo ou zero")
        @NotBlank(message = "O campo quantidade não pode ser nulo.")
        private final Integer quantidade;

        @NotBlank(message = "O campo marca não pode ser nulo.")
        private final MarcaDTO marca;

        public OculosDTO(@NotBlank(message = "O campo referência não pode ser nulo.") String referencia,
                        @NotBlank(message = "O campo nome não pode ser nulo.") @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Cor inválida.") String cor,
                        @NotBlank(message = "O campo tamanho não pode ser nulo.") String tamanho,
                        @NotNull(message = "O precoCusto tamanho não pode ser nulo.") @Positive(message = "O valor deve ser positivo") Double precoCusto,
                        @NotNull(message = "O campo precoVenda não pode ser nulo.") @Positive(message = "O valor deve ser positivo") Double precoVenda,
                        @PositiveOrZero(message = "O valor deve ser positivo ou zero") @NotBlank(message = "O campo quantidade não pode ser nulo.") Integer quantidade,
                        @NotBlank(message = "O campo marca não pode ser nulo.") MarcaDTO marca) {
                this.referencia = referencia;
                this.cor = cor;
                this.tamanho = tamanho;
                this.precoCusto = precoCusto;
                this.precoVenda = precoVenda;
                this.quantidade = quantidade;
                this.marca = marca;
        }

        public String getReferencia() {
                return referencia;
        }

        public String getCor() {
                return cor;
        }

        public String getTamanho() {
                return tamanho;
        }

        public Double getPrecoCusto() {
                return precoCusto;
        }

        public Double getPrecoVenda() {
                return precoVenda;
        }

        public Integer getQuantidade() {
                return quantidade;
        }

        public MarcaDTO getMarca() {
                return marca;
        }

        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
                result = prime * result + ((cor == null) ? 0 : cor.hashCode());
                result = prime * result + ((tamanho == null) ? 0 : tamanho.hashCode());
                result = prime * result + ((precoCusto == null) ? 0 : precoCusto.hashCode());
                result = prime * result + ((precoVenda == null) ? 0 : precoVenda.hashCode());
                result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
                result = prime * result + ((marca == null) ? 0 : marca.hashCode());
                return result;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                OculosDTO other = (OculosDTO) obj;
                if (referencia == null) {
                        if (other.referencia != null)
                                return false;
                } else if (!referencia.equals(other.referencia))
                        return false;
                if (cor == null) {
                        if (other.cor != null)
                                return false;
                } else if (!cor.equals(other.cor))
                        return false;
                if (tamanho == null) {
                        if (other.tamanho != null)
                                return false;
                } else if (!tamanho.equals(other.tamanho))
                        return false;
                if (precoCusto == null) {
                        if (other.precoCusto != null)
                                return false;
                } else if (!precoCusto.equals(other.precoCusto))
                        return false;
                if (precoVenda == null) {
                        if (other.precoVenda != null)
                                return false;
                } else if (!precoVenda.equals(other.precoVenda))
                        return false;
                if (quantidade == null) {
                        if (other.quantidade != null)
                                return false;
                } else if (!quantidade.equals(other.quantidade))
                        return false;
                if (marca == null) {
                        if (other.marca != null)
                                return false;
                } else if (!marca.equals(other.marca))
                        return false;
                return true;
        }

}
