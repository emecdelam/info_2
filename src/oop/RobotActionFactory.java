package oop;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Your task is to control a robot using a sequence of textual
 * commands. The robot can move forward, turn left, or turn right. The
 * robot is controlled through the following set of 4 basic commands:
 *
 * - FORWARD
 *   => Move the robot forward
 *
 * - LEFT
 *   => Turn the robot to the left
 *
 * - RIGHT
 *   => Turn the robot to the right
 *
 * - REPEAT N
 *   ...
 *   END REPEAT
 *   => Repeat "N" times the commands "..."
 *
 * For instance, here is a sample sequence of textual commands:
 *
 *  FORWARD
 *  REPEAT 3
 *  FORWARD
 *  RIGHT
 *  END REPEAT
 *  FORWARD
 *  FORWARD
 *
 * If applied to a robot that turns at right angles, this sample
 * sequence would generate the following pattern, where "x" denotes
 * the starting point of the robot, and "^" is its final position:
 *
 *      ^
 *      |
 *      |
 * x---->---->
 *      |    |
 *      |    |
 *      <----<
 *
 * Pay attention to the fact that the "REPEAT" commands can be nested
 * (i.e. a "REPEAT" command may recursively contain other "REPEAT"
 * commands).
 *
 * Using the "Factory" design pattern, you must convert an array of
 * strings containing commands into an "Action" object that can be
 * used to control one instance of the "Robot" interface.
 **/

public class RobotActionFactory {

    /**
     * Interface defining an abstract robot to be controlled.
     **/
    public static interface Robot {

        /**
         * Move the robot forward.
         **/
        void moveForward();

        /**
         * Turn the robot to the left.
         **/
        void turnLeft();

        /**
         * Turn the robot to the right.
         **/
        void turnRight();
    }


    /**
     * Interface defining an abstract action that modifies the state
     * of the given robot. An action can correspond to one isolated
     * command (move forward, turn left, or turn right), to one
     * sequence of actions, or to one repetition of an action.
     **/
    public static interface Action {

        /**
         * Apply this action to the given robot.
         * @param robot The robot.
         **/
        void apply(Robot robot);
    }


    /**
     * This type of "Action" moves the robot forward.
     **/
    private static class MoveForwardAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.moveForward();
        }
    }


    /**
     * This type of "Action" turns the robot to the left.
     **/
    private static class TurnLeftAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.turnLeft();
        }
    }


    /**
     * This type of "Action" turns the robot to the right.
     **/
    private static class TurnRightAction implements Action {
        @Override
        public void apply(Robot robot) {
            robot.turnRight();
        }
    }


    /**
     * This type of "Action" represents a sequence of actions to be
     * applied to the robot.
     **/
    private static class SequenceOfActions implements Action {
        private List<Action> actions = new LinkedList<Action>();
        public int length = 0;

        /**
         * Append a new action to the end of the sequence of actions.
         * @param action The action to be added.
         **/
        public void add(Action action) {
            actions.add(action);
            length++;
        }

        @Override
        public void apply(Robot robot) {
            for (Action action : actions) {
                action.apply(robot);
            }
        }
    }


    /**
     * This type of "Action" executes another action, for a given
     * number of times.
     **/
    private static class RepeatAction implements Action {
        private int times;
        private Action action;

        /**
         * Constructor for a repetition of one action.
         * @param times The number of times the action must be executed.
         * @param action The action to be repeated.
         **/
        RepeatAction(int times,
                     Action action) {
            this.times = times;
            this.action = action;
        }

        @Override
        public void apply(Robot robot) {
            for (int i = 0; i < times; i++) {
                action.apply(robot);
            }
        }
    }

    /**
     * The factory method you have to implement.
     *
     * NB 1: In order to parse an integer from some string "s", you
     * can use the standard function "Integer.parseInt(s)".
     *
     * NB 2: If the array of commands cannot be parsed (e.g. because
     * of an unknown action, or because of a "REPEAT" command without
     * an "END REPEAT"), you must throw an exception of class
     * "IllegalArgumentException".
     *
     * @param commands The array of commands to drive the robot.
     * @return An "Action" object that will move the robot
     * according to the commands.
     **/
    public Action parse(String[] commands) {
        SequenceOfActions sequence = new SequenceOfActions();
        if (Arrays.stream(commands)
                .mapToInt(command -> command.startsWith("REPEAT") ? 1 : command.startsWith("END") ? -1 : 0)
                .sum() > 0){throw new IllegalArgumentException("Missing END statement");}
        for (int i = 0; i < commands.length; i++) {
            switch (commands[i].split(" ")[0]) {
                case "FORWARD" -> {
                    if (commands[i].split(" ").length > 1) {throw new IllegalArgumentException("FORWARD can't be followed by numbers");}
                    sequence.add(new MoveForwardAction());
                }
                case "LEFT" -> {
                    if (commands[i].split(" ").length > 1) {throw new IllegalArgumentException("LEFT can't be followed by numbers");}
                    sequence.add(new TurnLeftAction());
                }
                case "RIGHT" -> {
                    if (commands[i].split(" ").length > 1) {throw new IllegalArgumentException("RIGHT can't be followed by numbers");}
                    sequence.add(new TurnRightAction());
                }
                case "REPEAT" -> {
                    int times = Integer.parseInt(commands[i].split(" ")[1]);
                    SequenceOfActions actionSequence = (SequenceOfActions) parse(Arrays.copyOfRange(commands, i + 1 /*i++ breaks obv*/, commands.length));
                    sequence.add(new RepeatAction(times, actionSequence));
                    sequence.length += actionSequence.length;
                    i += actionSequence.length + 1;
                }
                case "END" -> {
                    return sequence;
                }
            }
        }
        return sequence;
    }
}
