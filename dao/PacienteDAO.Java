public class PacienteDAO  {
    
    public void inserir(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome, cpf, telefone) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.PreparedStatement(sql)) {
                    stmt.setString(1, paciente.getNome());
                    stmt.setString(2, paciente.getCpf());
                    stmt.setString(3, paciente.getTelefone());
                    stmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println("Erro ao inserir paciente: " + e.getMessage());
                }
    }

    public void atualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nome = ?, cpf = ?, telefone = ?  WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.PreparedStatement(sql)) {

                    stmt.setString(1, paciente.getNome());
                    stmt.setString(2, paciente.getCpf());
                    stmt.setString(3, paciente.getTelefone());
                    stmt.setInt(4, paciente.getId());
                    stmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println("Erro ao atualizar paciente: " + e.getMessage());
                }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM paciente WHERE id = ?";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.PreparedStatement(sql)) {

                    stmt.setInt(1, id);
                    stmt.executeUpdate();

                } catch (SQLException e) {
                    System.out.println("Erro ao excluir paciente: " + e.getMessage());
                }
    }

    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {
                        Paciente p = new Paciente();
                        p.setId(rs.getInt("id"));
                        p.setNome(rs.getString("nome"));
                        p.setCpf(rs.getString("cpf"));
                        p.setTelefone(rs.getString("telefone"));
                        lista.add(p);
                    }

                } catch (SQLException e) {
                    System.out.println("Erro ao listar pacientes: " + e.getMessage());
                }

                return lista;
    }

    public Paciente buscarPorId(int id) {
        String sql = "SELECT * FROM paciente WHERE id = ?";
        Paciente p = null;

        try (Connection conn = Conexao.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                    stmt.setInt(1, id);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        p = new Paciente();
                        p.setId(rs.getInt("id"));
                        p.setNome(rs.getString("nome"));
                        p.setCpf(rs.getString("cpf"));
                        p.setTelefone(rs.getString("telefone"));
                    }

                } catch (SQLException e) {
                    System.out.println("Erro ao buscar paciente: " + e.getMessage());
                }

                return p;
    }
}