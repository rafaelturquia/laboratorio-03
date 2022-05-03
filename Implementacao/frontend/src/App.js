import { useState, useEffect } from 'react'
import './App.css';


const HEADERS = {
  'Accept': 'application/json',
  'Content-Type': 'application/json'
}

const App = () => {
  const [clients, setClients] = useState([])
  const [nome, setNome] = useState('')
  const [senha, setSenha] = useState('')
  const [id, setId] = useState(null)
  const [update, setUpdate] = useState(false)

  useEffect(() => fetch('clients').then(res => res.json())
    .then(res => {
      setSenha('')
      setNome('')
      setClients(res)
    })
    , [update])

  const salvarUsuario = () => fetch('clients', {
    method: 'POST',
    headers: HEADERS,
    body: JSON.stringify({ name: nome, email: senha })
  }).then(() => setUpdate(!update))

  const editarUsuario = () => fetch('clients/' + id, {
    method: 'PUT',
    headers: HEADERS,
    body: JSON.stringify({ name: nome, email: senha })
  }).then(() => setUpdate(!update))

  const removeClient = (id, setUpdate, update) => fetch('clients/' + id, {
    method: "DELETE", 
    headers: HEADERS,
  }).then(() => setUpdate(!update))


  return (
    <div className="App">
      <div>
        <h2>{id ? "Editar usuário " + id : "Adicionar usuário"}</h2>
        <input placeholder="Nome" type="text" value={nome} onChange={e => setNome(e.target.value)} />
          <input placeholder="Senha" type="password" value={senha} onChange={e => setSenha(e.target.value)} />
          <button type="button" onClick={() => id ? editarUsuario() : salvarUsuario()}>Salvar usuário</button>
      </div>
        <h2>Usuários cadastrados</h2>
        {clients.map((e, i) =>  
          <p key={i}>{e.id + ' - ' + e.name + ' -' + e.name}
            <button onClick={() => {
              setId(e.id)
              setNome(e.name)
              setSenha(e.email)
            }}>Editar</button>
            <button onClick={() => removeClient(e.id, setUpdate, update)}>Excluir</button>
          </p>)}
      </div>
      );
    }
    
    export default App
