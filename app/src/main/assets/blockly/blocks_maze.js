Blockly.Blocks['turn'] = {
  init: function() {
    var tournera = JavaMainActivity.get_tourner_a();
    var g = JavaMainActivity.get_gauche();
    var d = JavaMainActivity.get_droite();
    this.appendDummyInput()
        .appendField(tournera)
        .appendField(new Blockly.FieldDropdown([[g, "L"], [d, "R"]]), "NAME");
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(160);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};

Blockly.Blocks['move'] = {

  init: function() {
  var avancer = JavaMainActivity.get_avancer_case();
    this.appendDummyInput()
        .appendField(avancer);
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
  var chemin = JavaMainActivity.get_si_chemin();
  var dev = JavaMainActivity.get_devant();
  var ad = JavaMainActivity.get_a_droite();
  var ag = JavaMainActivity.get_a_gauche();
  var f = JavaMainActivity.get_faire();
    this.appendDummyInput()
        .appendField(chemin)
        .appendField(new Blockly.FieldDropdown([[dev, "F"], [ag, "L"], [ad, "R"]]), "DIR");
    this.appendStatementInput("THEN")
        .setCheck(null)
        .appendField(f);
    this.setPreviousStatement(true, null);
    this.setNextStatement(true, null);
    this.setColour(290);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
 };


Blockly.Blocks['while'] = {
  init: function() {
  var f = JavaMainActivity.get_faire();
    var tantque = JavaMainActivity.get_tant_que();
    this.appendDummyInput()
        .appendField(tantque);
    this.appendStatementInput("NAME")
        .setCheck(null)
        .appendField(f);
    this.setColour(330);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};


Blockly.Blocks['ifelse'] = {
  init: function() {
    var chemin = JavaMainActivity.get_si_chemin();
    var dev = JavaMainActivity.get_devant();
    var ad = JavaMainActivity.get_a_droite();
    var ag = JavaMainActivity.get_a_gauche();
    var f = JavaMainActivity.get_faire();
    var s = JavaMainActivity.get_sinon();
    this.appendDummyInput()
        .appendField(chemin)
        .appendField(new Blockly.FieldDropdown([[dev, "F"], [ag, "L"], [ad, "R"]]), "NAME");
    this.appendStatementInput("then")
        .setCheck(null)
        .appendField(f);
    this.appendStatementInput("else")
        .setCheck(null)
        .appendField(s);
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
    //JavaTermBuilder.reset();
   console.log(code);
   eval(code);
   JavaTermBuilder.eval();
};

function evalRestOfBlock () {
   JavaTermBuilder.eval();
};

function nextStep () {
   var code = Blockly.JavaScript.workspaceToCode(Blockly.mainWorkspace);
  //JavaTermBuilder.reset();
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

function initBlockly() {
    var toolbox = document.getElementById("toolbox");
    toolbox.innerHTML = "";
    var content = "";
    var blocksURL = document.location.href.toString().split("=");
    var blocks = blocksURL[1].split(",");
    var nmax = parseInt(blocksURL[2]);
    console.log(blocks);
    for ( var i = 0; i < blocks.length; i++) {
        content += "<block type=\"" + blocks[i] + "\"></block>\n";
    };
    console.log(content);
    toolbox.innerHTML = content;



    workspace = Blockly.inject('blocklyDiv',
            {media: 'media/',
            maxBlocks: nmax,
            zoom : { control: false,
                     wheel: false,
                     startScale : 0.90
                     },
             trashcan: true,
             toolbox: document.getElementById('toolbox')});

};
window.addEventListener("load", initBlockly);

