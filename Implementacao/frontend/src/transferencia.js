import { useState, useEffect } from 'react'
import { Button, Line } from 'arwes'
import { HEADERS } from './crudes'

const Transferencia = (props) => {
    const [transferencia, setTransferencia] = useState({ motivo: '', valor: 0 })
    const [alunos, setAlunos] = useState([])
    const [aluno, setAluno] = useState(null)

    useEffect(() => fetch('aluno/all').then(res => res.json())
        .then(res => setAlunos(res)), [])

    useEffect(() => {
        if (alunos.length > 0) setAluno(alunos[0])
    }, [alunos])

    const request = () => fetch(`transferenciaDePontos/${aluno.id}/${props.user.id}`, {
        method: 'POST',
        headers: HEADERS,
        body: JSON.stringify(transferencia)
    })
        .then(res => res.text())
        .then(res => alert(res))

    return (<div className="campos">
        <h2>Transferência</h2>
        {alunos.length === 0 && <div>Buscando alunos</div>}
        {alunos.length > 0 && <><input placeholder="valor" type="number" value={transferencia.valor}
            onChange={e => setTransferencia({ ...transferencia, valor: e.target.value })} />
            <input placeholder="motivo" type="text" value={transferencia.motivo}
                onChange={e => {
                    let itemAtualizado = { ...transferencia }
                    itemAtualizado.motivo = e.target.value
                    setTransferencia(itemAtualizado)
                }} />
            {aluno !== null && <div style={{ display: 'flex', flexFlow: 'row' }}>
                <label style={{ padding: '0px 10px', fontSize: '18px' }}>Aluno:</label>
                <select value={aluno} onChange={(evt) => {
                    setAluno(evt.target.value)
                    setTransferencia({ ...transferencia, aluno_id: evt.target.value.id })
                }}>
                    {alunos.map((e, i) => <option key={i} value={e.nome}>{e.nome}</option>)}
                </select></div>}
            <Button animate layer='success' type="button" enabled={transferencia.motivo !== "" && transferencia.valor !== 0}
                onClick={() => request()}>Transferir</Button>
        </>}
    </div >
    )
}

export default Transferencia