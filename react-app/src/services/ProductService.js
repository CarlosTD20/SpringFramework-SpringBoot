import axios from "axios"

const initProducts = [
    {
        id: 1,
        name: 'Monitor Samsung',
        price: 500,
        description: 'Este monitor es lo mejor'
    },
    {
        id: 2,
        name: 'Iphone 14',
        price: 800,
        description: 'El telefono es muy bueno'
    }
]

const baseUrl = 'http://localhost:8080/products'

export const listProducts = () => {
    return initProducts
}

export const findAll = async () => {
    try {
        const response = await axios.get(baseUrl)
        return response
    } catch (err) {
        console.log(err)
    }

    return null
}

export const create = async ({name, description, price}) => {
    try {
        const response = await axios.post(baseUrl, {
            name,
            description,
            price
        })
        return response
    } catch (err) {
        console.log(err)
    }

    return undefined
}

export const update = async ({id, name, description, price}) => {
    try {
        const response = await axios.put(`${baseUrl}/${id}`, {
            name,
            description,
            price
        })
        return response
    } catch (err) {
        console.log(err)
    }

    return undefined
}


export const remove = async (id) => {
    try {
        await axios.delete(`${baseUrl}/${id}`)
    } catch (err) {
        console.log(err)
    }
}