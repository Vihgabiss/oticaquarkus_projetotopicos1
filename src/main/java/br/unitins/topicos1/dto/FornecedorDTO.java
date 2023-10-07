package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Telefone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class FornecedorDTO {

        @NotBlank(message = "O campo nome não pode ser nulo.")
        private final String nome;
        @NotBlank(message = "O campo telefone não pode ser nulo.")
        private final Telefone telefone;
        @NotBlank(message = "O campo email não pode ser nulo.")
        @Email(message = "Email inválido.")
        private final String email;
        @NotBlank(message = "O campo endereço não pode ser nulo.")
        private final Endereco endereco;
        @NotBlank(message = "O campo cnpj não pode ser nulo.")
        @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", message = "CNPJ inválido")
        private final String cnpj;

        public FornecedorDTO(String nome, Telefone telefone, String email, Endereco endereco, String cnpj) {
                this.nome = nome;
                this.telefone = telefone;
                this.email = email;
                this.endereco = endereco;
                this.cnpj = cnpj;
        }

        public String getNome() {
                return nome;
        }

        public Telefone getTelefone() {
                return telefone;
        }

        public String getEmail() {
                return email;
        }

        public Endereco getEndereco() {
                return endereco;
        }

        public String getCnpj() {
                return cnpj;
        }

        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((nome == null) ? 0 : nome.hashCode());
                result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
                result = prime * result + ((email == null) ? 0 : email.hashCode());
                result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
                result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
                FornecedorDTO other = (FornecedorDTO) obj;
                if (nome == null) {
                        if (other.nome != null)
                                return false;
                } else if (!nome.equals(other.nome))
                        return false;
                if (telefone == null) {
                        if (other.telefone != null)
                                return false;
                } else if (!telefone.equals(other.telefone))
                        return false;
                if (email == null) {
                        if (other.email != null)
                                return false;
                } else if (!email.equals(other.email))
                        return false;
                if (endereco == null) {
                        if (other.endereco != null)
                                return false;
                } else if (!endereco.equals(other.endereco))
                        return false;
                if (cnpj == null) {
                        if (other.cnpj != null)
                                return false;
                } else if (!cnpj.equals(other.cnpj))
                        return false;
                return true;
        }

        public Long getId() {
                return null;
        }

}
