import { useState } from 'react'

const Transferencia = (props) => {
    const [transferencia, setTransferencia] = useState({ motivo: '', valor: 0 })

    return (<div className="campos">
        <input key={i} placeholder="valor" type="number" value={transferencia.valor}
            onChange={e => {
                let itemAtualizado = { ...estado }
                itemAtualizado.valor = e.target.value
                setTransferencia(itemAtualizado)
            }} />
        <h2>Transferência</h2>
        <input key={i} placeholder="motivo" type="text" value={transferencia.motivo}
            onChange={e => {
                let itemAtualizado = { ...estado }
                itemAtualizado.motivo = e.target.value
                setTransferencia(itemAtualizado)
            }} />
        <Button animate layer='success' type="button" enabled={transferencia.motivo == "" && transferncia.valor !== 0}
            onClick={() => console.log("transferir")}>Transferir</Button>
    </div >
    )
}

export default Transferencia