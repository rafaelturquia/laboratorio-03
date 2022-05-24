import { useState, useEffect } from 'react'
import Crud from './crudes'
import Login from './login'
import Transferencia from './transferencia'
import { ThemeProvider, createTheme, Arwes, Puffs } from 'arwes';
import { HEADERS } from './crudes'
import ListaDeVantagens from './listaDeVantagens';
import Extrato from './extrato'

const styling = {
  cursor: 'pointer',
  color: '#26dafd',
  textDecoration: 'underline'
}

const userDefault = {
  papel: "nenhum",
  isLogged: false,
  id: null,
  login: null,
  saldo: 0
}

const App = () => {
  const [page, setPage] = useState("nada")
  const [user, setUser] = useState(userDefault)
  const [update, setUpdate] = useState(false)

  const request = () => fetch(`${user.papel}/logout/${user.id}`, {
    method: "PUT",
    headers: HEADERS,
    body: JSON.stringify(user)
  }).then(res => {
    if (res.status === 200) {
      setUser(userDefault)
      setUpdate(!update)
      setPage('nada')
    }
  })

  useEffect(() => setUpdate(!update), [JSON.stringify(user)])

  return (
    <ThemeProvider theme={createTheme()}>
      <Arwes>
        <Puffs>
          <div className="App">
            {!user.isLogged && <>
              <div className="App2">
                <div style={styling} onClick={() => setPage("aluno")}>Cadastrar Aluno</div>
                <div style={styling} onClick={() => setPage("empresa")}>Cadastrar Empresa Parceira</div>
                <div style={styling} onClick={() => setPage("login")}>Login</div>
              </div>
              {(page === "aluno" || page === "empresa") && <Crud oQue={page} />}
              {page === "login" && <Login setUser={setUser} />}
            </>}
            {user.isLogged && <>
              <div className="App2">
                {(user.papel === "professor" || user.papel === "aluno") && <div style={styling} onClick={() => setPage("extrato")}>Verificar Extrato</div>}
                {user.papel === "professor" && <div style={styling} onClick={() => setPage("transferencia")}>Realizar transferência</div>}
                {user.papel === "empresa" && <div style={styling} onClick={() => setPage("vantagem")}>Cadastrar vantagem</div>}
                {user.papel === "aluno" && <div style={styling} onClick={() => setPage("listaDeVantagens")}>Lista de Vantagens</div>}
                <div style={styling} onClick={() => request()}>Logout</div>
              </div>
              {page === "extrato" && <Extrato user={user} setUser={setUser}/>}
              {page === "vantagem" && <Crud oQue={page} user={user} />}
              {page === "transferencia" && <Transferencia user={user} />}
              {page === "listaDeVantagens" && <ListaDeVantagens user={user} />}
            </>
            }
          </div>
        </Puffs>
      </Arwes>
    </ThemeProvider>
  );
}

export default App
