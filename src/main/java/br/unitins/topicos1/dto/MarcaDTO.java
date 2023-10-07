package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public class MarcaDTO {
        @NotBlank(message = "O campo nome não pode ser nulo.")
        private final String nome;
        @NotBlank(message = "O campo fornecedor não pode ser nulo.")
        private final FornecedorDTO fornecedor;

        public MarcaDTO(@NotBlank(message = "O campo nome não pode ser nulo.") String nome,
                        @NotBlank(message = "O campo fornecedor não pode ser nulo.") FornecedorDTO fornecedor) {
                this.nome = nome;
                this.fornecedor = fornecedor;
        }

        public String getNome() {
                return nome;
        }

        public FornecedorDTO getFornecedor() {
                return fornecedor;
        }

        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((nome == null) ? 0 : nome.hashCode());
                result = prime * result + ((fornecedor == null) ? 0 : fornecedor.hashCode());
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
                MarcaDTO other = (MarcaDTO) obj;
                if (nome == null) {
                        if (other.nome != null)
                                return false;
                } else if (!nome.equals(other.nome))
                        return false;
                if (fornecedor == null) {
                        if (other.fornecedor != null)
                                return false;
                } else if (!fornecedor.equals(other.fornecedor))
                        return false;
                return true;
        }

}
