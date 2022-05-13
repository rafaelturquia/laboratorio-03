import { useState } from 'react'
import Crud from './crudes'
import Login from './login'
import Transferencia from './transferencia'
import { ThemeProvider, createTheme, Arwes, Puffs } from 'arwes';
import { HEADERS } from './crudes'

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
}

const App = () => {
  const [page, setPage] = useState("nada")
  const [user, setUser] = useState(userDefault)
  const [update, setUpdate] = useState(false)

  const request = () => fetch(user.papel + '/logout', {
    method: "PUT",
    headers: HEADERS,
    body: JSON.stringify(user)
  }).then(res => {
    if (res.status === 200) {
      setUser(userDefault)
      setUpdate(!update)
    }
  })

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
                <div style={styling} onClick={() => setPage("extrato")}>Verificar Extrato</div>
                {user.papel === "professor" && <div style={styling} onClick={() => setPage("transferencia")}>Realizar transferência</div>}
                <div style={styling} onClick={() => request()}>Logout</div>
              </div>
              {page === "transferencia" && <Transferencia user={user}/>}
            </>
            }
          </div>
        </Puffs>
      </Arwes>
    </ThemeProvider>
  );
}

export default App
