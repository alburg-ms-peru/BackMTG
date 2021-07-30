package pe.com.pacifico.kuntur.business.threads;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.pacifico.kuntur.repository.ProcesoJpaRepoository;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class HiloFase3Test {

    @Mock
    private HiloFase3 mockHiloFase3 = mock(HiloFase3.class);

    @Mock
    private ProcesoJpaRepoository procesoJpaRepository = mock(ProcesoJpaRepoository.class);

    private int periodo = 202001;
    private int repartoTipo_R = 1;

    @Test
    public void shouldBegin(){
        // When
        lenient().doNothing().when(mockHiloFase3).start();
        // Then
        HiloFase3.begin(procesoJpaRepository, repartoTipo_R, periodo);
    }

}
