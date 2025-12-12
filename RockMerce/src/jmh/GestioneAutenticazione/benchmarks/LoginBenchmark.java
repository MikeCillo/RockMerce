// java
package GestioneAutenticazione.benchmarks;

import LogicTier.Entità.Customer;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(3)
public class LoginBenchmark {
    @State(Scope.Benchmark)
    public static class LoginState {
        private  AutenticazioneServiceAdapter loginService;
        public final String validUsername = "test_user";
        public final String validPassword = "secure_password";

        @Setup(Level.Trial)
        public void setup() {
            this.loginService = new AutenticazioneServiceAdapter();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Threads(1)
    public Customer benchmark_Login_SingleThread_Latency(LoginState state, Blackhole bh) {
        Customer customer = state.loginService.customerLogIn(state.validUsername, state.validPassword);
        bh.consume(customer);
        return customer;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Threads(32)
    public Customer benchmark_Login_PeakLoad_Throughput(LoginState state, Blackhole bh) {
        Customer customer = state.loginService.customerLogIn(state.validUsername, state.validPassword);
        bh.consume(customer);
        return customer;
    }
}
