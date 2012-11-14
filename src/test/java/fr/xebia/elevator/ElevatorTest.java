package fr.xebia.elevator;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ElevatorTest {

    private Elevator elevator;

    @Before
    public void createElevator() throws Exception {
        elevator = new Elevator();
    }

    @Test
    public void should_start_at_floor_zero() {
        final Integer floor = elevator.floor();

        assertThat(floor).isEqualTo(0);
    }

    @Test
    public void should_start_with_closed_door() {
        final Boolean doorsOpen = elevator.isDoorsOpen();

        assertThat(doorsOpen).isFalse();
    }

    @Test
    public void should_does_nothing_when_nobody_call_elevator() {
        elevator.tick();

        assertThat(elevator.floor()).isEqualTo(0);
        assertThat(elevator.isDoorsOpen()).isFalse();
    }

    @Test
    public void should_open_doors() {
        elevator.call(0).tick();

        final Boolean doorsOpen = elevator.isDoorsOpen();

        assertThat(doorsOpen).isTrue();
    }

    @Test
    public void should_let_doors_open_when_open() {
        elevator.call(0).tick().tick();

        final Boolean doorsOpen = elevator.isDoorsOpen();

        assertThat(doorsOpen).isTrue();
    }

    @Test
    public void should_close_doors_after_open() {
        elevator.call(0).tick().tick().tick();

        final Boolean doorsOpen = elevator.isDoorsOpen();

        assertThat(doorsOpen).isFalse();
    }

    @Test
    public void should_go_to_called_floor() {
        final Integer floor = elevator.call(1).tick().floor();

        assertThat(floor).isEqualTo(1);
    }

}