// java
package GestioneCart.benchmarks;

import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
public class CartServiceAdapterBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Threads(20)
    public void benchmark_AddGuitarToCart_Throughput( CartState state) throws SQLException {
        state.cartService.addGuitarToCart(state.customer, state.guitarToAdd);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void benchmark_RemoveGuitarFromCart_Latency( CartState state, final Blackhole bh) throws SQLException {
        final Cart cartResult = state.cartService.removeGuitarFromCart(state.customer, state.guitarToAdd.getId());
        bh.consume(cartResult);
        state.cartService.addGuitarToCart(state.customer, state.guitarToAdd);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Threads(1)
    public void benchmark_AddGuitar_SingleThread(CartState state) throws SQLException {
        state.cartService.addGuitarToCart(state.customer, state.guitarToAdd);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Threads(1)
    public void benchmark_RemoveGuitar_SingleThread( CartState state,final Blackhole bh) throws SQLException {
        final Cart cartResult = state.cartService.removeGuitarFromCart(state.customer, state.guitarToAdd.getId());
        bh.consume(cartResult);
        state.cartService.addGuitarToCart(state.customer, state.guitarToAdd);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Threads(32)
    public void benchmark_AddGuitar_HighContention(final CartState state) throws SQLException {
        state.cartService.addGuitarToCart(state.customer, state.guitarToAdd);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Threads(32)
    public void benchmark_RemoveGuitar_HighContention(CartState state, Blackhole bh) throws SQLException {
        final Cart cartResult = state.cartService.removeGuitarFromCart(state.customer, state.guitarToAdd.getId());
        bh.consume(cartResult);
        state.cartService.addGuitarToCart(state.customer, state.guitarToAdd);
    }
}
