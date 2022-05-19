import { useState, useEffect } from 'react'
import './App.css';
import { Button, Line } from 'arwes'

export const HEADERS = {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
}

const alunosProps = ["login", "senha", "nome", "cpf", "email", "curso", "instituicao"]
const empresaProps = ["login", "senha", "nome", "cnpj"]
const vantagemProps = ["descricao", "valor"]

const Crud = (props) => {
    const [itens, setItens] = useState([])
    const [item, setItem] = useState({})
    const [id, setId] = useState(null)
    const [update, setUpdate] = useState(false)

    useEffect(() => fetch(`${props.oQue}/all${props.oQue === 'vantagem' ? "/" + props ?.user ?.id : ""}`).then(res => res.json())
        .then(res => {
            setItem({})
            setItens(res)
        }), [update, props.oQue])

    const request = (method, idRequest) => fetch(props.oQue + '/' + (idRequest ?? ''), {
        method: method,
        headers: HEADERS,
        body: JSON.stringify(item)
    }).then(() => {
        setItem({})
        setId(null)
        setUpdate(!update)
    })

    let quaisProps
    switch (props.oQue) {
        case 'aluno':
            quaisProps = alunosProps
            break
        case 'empresa':
            quaisProps = empresaProps
            break
        case 'vantagem':
            quaisProps = vantagemProps
    }

    return (
        <div>
            <div className="campos">
                <h2>{id ? "Editar " + props.oQue + " " + id : "Adicionar " + props.oQue}</h2>
                {quaisProps.map((prop, i) => <input key={i} placeholder={prop} type="text" value={item[prop]}
                    onChange={e => {
                        let itemAtualizado = { ...item }
                        itemAtualizado[prop] = e.target.value
                        setItem(itemAtualizado)
                    }} />)}
                {props.oQue === 'vantagem' && <input type="file" name="image" accept="image/png, image/jpeg" />}
                <Button animate layer='success' type="button" onClick={() => id ?
                    request('PUT', id) : request('POST', props.oQue === 'vantagem' ? props.user.id : null)}>Salvar {props.oQue}</Button>
            </div>
            <h2>{props.oQue}s cadastrados</h2>
            {itens ?.map((itemMapped, i) => <>
                <p key={i}>{Object.keys(itemMapped).map(prop => itemMapped[prop] + ' - ')}
                    <Button animate layer='success' onClick={() => {
                        setId(itemMapped.id)
                        setItem(itemMapped)
                    }}>Editar</Button>
                    <Button onClick={() => { request('DELETE', itemMapped.id) }}>Excluir</Button>
                </p>
                <Line animate layer='success' />
            </>)}
        </div>
    );
}

export default Crud


// simbolo banco de dados
// empresa parceira logar
// https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial