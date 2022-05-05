import { useState, useEffect } from 'react'
import './App.css';


const HEADERS = {
  'Accept': 'application/json',
  'Content-Type': 'application/json'
}

const App = () => {
  const [empresas, setEmpresas] = useState([])
  const [nome, setNome] = useState('')
  const [cnpj, setCnpj] = useState('')
  const [id, setId] = useState(null)
  const [update, setUpdate] = useState(false)

  useEffect(() => fetch('empresa/all').then(res => res.json())
    .then(res => {
      setCnpj('')
      setNome('')
      setEmpresas(res)
    })
    , [update])

  const salvarEmpresa = () => fetch('empresa/add', {
    method: 'POST',
    headers: HEADERS,
    body: JSON.stringify({ nome: nome, cnpj: cnpj })
  }).then(() => setUpdate(!update))

  const editarEmpresa = () => fetch('empresa/' + id, {
    method: 'PUT',
    headers: HEADERS,
    body: JSON.stringify({ nome: nome, cnpj: cnpj })
  }).then(() => setUpdate(!update))

  const removerEmpresa = (id, setUpdate, update) => fetch('empresa/' + id, {
    method: "DELETE",
    headers: HEADERS,
  }).then(() => setUpdate(!update))


  return (
    <div className="App">
      <div>
        <h2>{id ? "Editar empresa " + id : "Adicionar empresa"}</h2>
        <input placeholder="Nome" type="text" value={nome} onChange={e => setNome(e.target.value)} />
        <input placeholder="CNPJ" type="text" value={cnpj} onChange={e => setCnpj(e.target.value)} />
        <button type="button" onClick={() => id ? editarEmpresa() : salvarEmpresa()}>Salvar empresa</button>
      </div>
      <h2>Empresas cadastrados</h2>
      {empresas?.map((e, i) =>
        <p key={i}>{e.id + ' - ' + e.nome + ' -' + e.cnpj}
          <button onClick={() => {
            setId(e.id)
            setNome(e.nome)
            setCnpj(e.cnpj)
          }}>Editar</button>
          <button onClick={() => removerEmpresa(e.id, setUpdate, update)}>Excluir</button>
        </p>)}
    </div>
  );
}

export default App
