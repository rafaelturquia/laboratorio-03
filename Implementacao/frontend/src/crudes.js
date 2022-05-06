import { useState, useEffect } from 'react'
import './App.css';
import { Button, Line } from 'arwes'

const HEADERS = {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
}

const alunosProps = ["login", "senha", "nome", "cpf", "email", "curso", "instituicao"]
const empresaProps = ["nome", "cnpj"]

const Crud = (props) => {
    const [itens, setItens] = useState([])
    const [item, setItem] = useState({})
    const [id, setId] = useState(null)
    const [update, setUpdate] = useState(false)

    useEffect(() => fetch(props.oQue + '/all').then(res => res.json())
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

    const quaisProps = props.oQue === "aluno" ? alunosProps : empresaProps

    return (
        <div>
            <div className="campos">
                <h2>{id ? "Editar " + props.oQue + " " + id : "Adicionar " + props.oQue}</h2>
                {quaisProps.map((prop, i) =>
                    <input key={i} placeholder={prop} type="text" value={item[prop]}
                        onChange={e => {
                            let itemAtualizado = { ...item }
                            itemAtualizado[prop] = e.target.value
                            setItem(itemAtualizado)
                        }} />)}
                <Button animate layer='success' type="button" onClick={() => id ? request('PUT', id) : request('POST', null)}>Salvar {props.oQue}</Button>
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