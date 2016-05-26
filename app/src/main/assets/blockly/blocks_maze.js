Blockly.Blocks['turn'] = {
  init: function() {
    this.appendDummyInput()
        .appendField("tourner")
        .appendField(new Blockly.FieldDropdown([["à gauche", "OPTIONNAME"], ["à droite", "OPTIONNAME"]]), "NAME");
    this.setInputsInline(false);
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
        .appendField(new Blockly.FieldDropdown([["devant", "OPTIONDEVANT"], ["à gauche", "OPTIONGAUCHE"], ["à droite", "OPTIONDROITE"]]), "NAME");
    this.appendStatementInput("NAME")
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
        .appendField("S'il y a un chemin")
        .appendField(new Blockly.FieldDropdown([["devant", "OPTIONNAME1"], ["à droite", "OPTIONNAME2"], ["à gauche", "OPTIONNAME"]]), "NAME");
    this.appendStatementInput("then")
        .setCheck(null)
        .appendField("faire");
    this.appendStatementInput("else")
        .setCheck(null)
        .appendField("sinon");
    this.setInputsInline(false);
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(290);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};
//Term Builders
Blockly.JavaScript['turn'] = function(block) {
  var dropdown_name = block.getFieldValue('NAME');
   var code = 'JavaTermBuilder.pushTurnRL("' + dropdown_name + '");\n';
  return code;
};

Blockly.JavaScript['move'] = function(block) {

  var code = 'JavaTermBuilder.pushMove();\n';
  return code;
};

Blockly.JavaScript['ifpath'] = function(block) {
  var dropdown_name = block.getFieldValue('NAME');
   var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  var code ='JavaTermBuilder.pushIfThen();\n';
  return code;
};

Blockly.JavaScript['while'] = function(block) {
  var statements_name = Blockly.JavaScript.statementToCode(block, 'NAME');
  var code = 'JavaTermBuilder.pushWhile();\n';
  return code;

};
Blockly.JavaScript['ifelse'] = function(block) {
  var dropdown_name = block.getFieldValue('NAME');
  var statements_then = Blockly.JavaScript.statementToCode(block, 'then');
  var statements_else = Blockly.JavaScript.statementToCode(block, 'else');
  // TODO: Assemble JavaScript into code variable.
  var code ='JavaTermBuilder.pushIfThenElse();\n';
  return code;
};
function evalBlock () {
   var code = Blockly.JavaScript.workspaceToCode(Blockly.mainWorkspace);
   JavaTermBuilder.reset();
   console.log(code);
   eval(code);
   JavaTermBuilder.eval();
};