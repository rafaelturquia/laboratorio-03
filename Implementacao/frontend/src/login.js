
import { useState, useEffect } from 'react'
import { HEADERS } from './crudes'
import { Button, Line } from 'arwes'
import { Inputs } from './utils'

const loginProps = ["login", "senha"]
const roles = ["aluno", "professor", "empresa"]

const Login = (props) => {
    const [loginData, setLoginData] = useState({
        login: null,
        senha: null,
    })
    const [role, setRole] = useState(roles[0])
    const [update, setUpdate] = useState(false)

    const request = () => fetch(role + '/login', {
        method: "PUT",
        headers: HEADERS,
        body: JSON.stringify(loginData)
    }).then(res => {
        if (res.status === 200) return res.text()
        else alert('Houve um problema no login, tente novamente')
    }).then(resPrev => {
        try {
            let res = JSON.parse(resPrev)
            console.log('res', res)
            if (res ?.usuario_id === "-1") return alert('Usuário não encotrado')
            if (res ?.login !== "true") return alert('Senha incorreta tente novamente')
            let updateUser = {
                papel: role,
                login: loginData.login,
                isLogged: true,
                id: res.usuario_id
            }
            if (res ?.saldo) { updateUser.saldo = res ?.saldo}
            console.log('updateUser', updateUser)
            props.setUser(updateUser)
        } catch{
            console.log('error on login')
        }
    })

    return (<div className="campos">
        <h2>Login</h2>
        <Inputs arrayProps={loginProps} estado={loginData} setEstado={setLoginData} />
        <div style={{ display: 'flex', flexFlow: 'row' }}>
            {roles.map(e => <>
                <input type="radio" checked={e === role} onChange={() => setRole(e)} />
                <label style={{ fontSize: '18px', padding: '0px 20px 0px 5px' }}>{e}</label>
            </>)}</div>
        <Button animate layer='success' type="button" enabled={loginData.login && loginData.senha}
            onClick={() => request()}>Login</Button>
    </div >
    )
}

export default Login