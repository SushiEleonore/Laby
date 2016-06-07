Blockly.Blocks['turn'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("tourner à")
        .appendField(new Blockly.FieldDropdown([["gauche", "L"], ["droite", "R"]]), "NAME");
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(160);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};

Blockly.Blocks['move'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("avancer d'une case");
    this.setInputsInline(false);
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(160);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};




Blockly.Blocks['ifpath'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Si il y a un chemin")
        .appendField(new Blockly.FieldDropdown([["devant", "F"], ["à gauche", "L"], ["à droite", "R"]]), "DIR");
    this.appendStatementInput("THEN")
        .setCheck(null)
        .appendField("faire");
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(290);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
 };


Blockly.Blocks['while'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Tant que le niveau n'est pas fini,");
    this.appendStatementInput("NAME")
        .setCheck(null)
        .appendField("faire");
    this.setColour(330);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};


Blockly.Blocks['ifelse'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("Si il y a un chemin")
        .appendField(new Blockly.FieldDropdown([["devant", "F"], ["à gauche", "L"], ["à droite", "R"]]), "NAME");
    this.appendStatementInput("then")
        .setCheck(null)
        .appendField("faire");
    this.appendStatementInput("else")
        .setCheck(null)
        .appendField("else");
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(290);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};
//Term Builders
Blockly.JavaScript['turn'] = function(block) {

    console.log("Creating :" + block.id);
  var dropdown_name = block.getFieldValue('NAME');
   var code = 'JavaTermBuilder.pushTurnRL("' + dropdown_name + '", "'+ block.id +'" );\n';
  return code;
};


Blockly.JavaScript['move'] = function(block) {

    console.log("Creating :" + block.id);
  var code = 'JavaTermBuilder.pushMove("'+ block.id +'");\n';
  return code;
};

Blockly.JavaScript['ifpath'] = function(block) {

    console.log("Creating :" + block.id);
  var dropdown_name = block.getFieldValue('DIR');
  var statements_name = Blockly.JavaScript.statementToCode(block, 'THEN');
  var code ='JavaTermBuilder.pushBegin();' + statements_name + 'JavaTermBuilder.pushIfThen("'+dropdown_name+ '","'+ block.id +'");\n';
  return code;
};

Blockly.JavaScript['while'] = function(block) {

    console.log("Creating :" + block.id);
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  var code = 'JavaTermBuilder.pushBegin();' + statements_name + 'JavaTermBuilder.pushWhile("'+ block.id +'");\n';
  return code;
};

Blockly.JavaScript['ifelse'] = function(block) {

    console.log("Creating :" + block.id);
  var dropdown_name = block.getFieldValue('NAME');
  var statements_then = Blockly.JavaScript.statementToCode(block, 'then');
  var statements_else = Blockly.JavaScript.statementToCode(block, 'else');
  // MAY RAISE AN ERROR !
  var code ='JavaTermBuilder.pushBegin();' + statements_else+'JavaTermBuilder.pushBegin();' + statements_then +'JavaTermBuilder.pushIfThenElse("'+dropdown_name+'" , "'+ block.id +'");\n';
  return code;
};

function evalBlock () {
   var code = Blockly.JavaScript.workspaceToCode(Blockly.mainWorkspace);
   JavaTermBuilder.reset();
   console.log(code);
   eval(code);
   JavaTermBuilder.eval();
};

function nextStep () {
   var code = Blockly.JavaScript.workspaceToCode(Blockly.mainWorkspace);
   JavaTermBuilder.reset();
   console.log(code);
   eval(code);
   JavaTermBuilder.nextStep();
};

function prevStep () {
    //console.log("previous step is here");
   JavaTermBuilder.prevStep();
};

function highlightBlockById (id){

     Blockly.mainWorkspace.getAllBlocks().forEach( function (o,i,_) { o.unselect(); });
      //console.log("Highlighting2 :" + id);
      Blockly.mainWorkspace.getBlockById(id).select();

};