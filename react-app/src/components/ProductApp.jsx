import { useEffect, useState } from "react"
import { listProducts } from "../services/ProductService"
import { ProductGrid } from "./ProductGrid"
import { PropTypes } from "prop-types"
import { ProductForm } from "./ProductForm"

export const ProductApp = ({ title }) => {

    const [products, setProducts] = useState([])

    const [productSelected, setProductSelected] = useState({
        id: 0,
        name: '',
        description: '',
        price: ''
    })

    useEffect(() => {
        const result = listProducts()
        setProducts(result)
    }, [])

    const handlerProduct = (product) => {
        // console.log(product)

        if (product.id > 0) {
            setProducts(products.map(prod => {
                if (prod.id == product.id) {
                    return { ...product }
                }
                return prod
            }))
        } else {
            setProducts([...products, { ...product, id: new Date().getTime() }])
        }
    }

    const handlerRemove = (id) => {
        console.log(id)
        setProducts(products.filter(prod => prod.id != id))
    }

    const handlerProductSelected = (product) => {
        setProductSelected({ ...product })
    }

    return (
        <divc lassName="container my-4">
            <h2>{title}</h2>
            <div className="row">
                <div className="col">
                    <ProductForm handlerAdd={handlerProduct} productSelected={productSelected} />
                </div>
                <div className="col">
                    {
                        products.length > 0 ? <ProductGrid products={products} handlerRemove={handlerRemove} handlerProductSelected={handlerProductSelected} /> : <div className="alert alert-warning">No hay productos en el sistema!!!</div>
                    }

                </div>
            </div>
        </divc>
    )
}

ProductApp.propTypes = {
    title: PropTypes.string.isRequired
}