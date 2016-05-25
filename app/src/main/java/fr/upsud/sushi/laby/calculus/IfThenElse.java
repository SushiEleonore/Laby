package fr.upsud.sushi.laby.calculus;

/**
 * Created by proval on 5/25/16.
 */
public class IfThenElse extends Instr {
        private Bool cond;
        private ListInstr instrThen;
        private ListInstr instrElse;

        public IfThenElse(Bool c, ListInstr lThen, ListInstr lElse) {
            this.cond = c;
            this.instrThen = lThen;
            this.instrElse = lElse;
        }

        @Override
        public void eval(){
            boolean b = cond.eval();
            if (b) {
                this.instrThen.eval();
            } else {
                this.instrElse.eval();
            }

        }
}


