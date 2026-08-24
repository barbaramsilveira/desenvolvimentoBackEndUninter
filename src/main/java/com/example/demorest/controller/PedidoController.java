package com.example.demorest.controller;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.example.demorest.model.Cliente;
import com.example.demorest.model.Pedido;
import com.example.demorest.model.Produto;
import com.example.demorest.repository.ClienteRepository;
import com.example.demorest.repository.PedidoRepository;
import com.example.demorest.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoController(PedidoRepository pedidoRepository,
                            ProdutoRepository produtoRepository,
                            ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable long id) {
        return pedidoRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Map<String, Object> payload) {
        try {
            Integer quantidade = (Integer) payload.get("quantidade");
            BigDecimal preco = new BigDecimal(payload.get("preco").toString());
            String produtoNome = (String) payload.get("produtoNome");
            String clienteNome = (String) payload.get("clienteNome");
            String ru = (String) payload.get("ru");

            List<Produto> produtos = produtoRepository.findByNome(produtoNome);
            if (produtos.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Produto produto = produtos.get(0);

            Cliente cliente = clienteRepository.findByNomeAndRu(clienteNome, ru);
            if (cliente == null) {
                return ResponseEntity.badRequest().build();
            }

            Pedido pedido = new Pedido();
            pedido.setQuantidade(quantidade);
            pedido.setPreco(preco);
            pedido.setProduto(produto);
            pedido.setCliente(cliente);

            Pedido saved = pedidoRepository.save(pedido);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> update(@PathVariable("id") long id, @RequestBody Pedido pedido) {
        return pedidoRepository.findById(id).map(record -> {
            record.setProduto(pedido.getProduto());
            record.setCliente(pedido.getCliente());
            record.setQuantidade(pedido.getQuantidade());
            record.setPreco(pedido.getPreco());
            Pedido updated = pedidoRepository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = { "/{id}" })
    public ResponseEntity<?> delete(@PathVariable long id) {
        return pedidoRepository.findById(id).map(record -> {
            pedidoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
