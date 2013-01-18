package ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.IfThenElse;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.Spreadsheet;
import spreadsheet.arithmetic.Add;
import spreadsheet.arithmetic.Int;
import spreadsheet.arithmetic.Neg;
import spreadsheet.arithmetic.Sum;
import spreadsheet.exception.NoSuchSpreadsheet;
import spreadsheet.logical.And;
import spreadsheet.logical.Eq;
import spreadsheet.logical.False;
import spreadsheet.logical.Lt;
import spreadsheet.logical.Not;
import spreadsheet.logical.Or;
import spreadsheet.logical.True;
import spreadsheet.textual.Concat;
import spreadsheet.textual.Text;
import ui.exception.IllegalStartOfExpression;
import ui.exception.InvalidExpression;
import ui.exception.InvalidPosition;

public final class ExpressionInterpreter {

  private ExpressionInterpreter() {
    // This class should not be instantiated.
  }
  
  /**
   * @param text assumed not null.
   * @return null if the command was not recognized.
   */
  public static Expression interpret(final String text)
      throws
      InvalidPosition,
      NoSuchSpreadsheet,
      IllegalStartOfExpression,
      InvalidExpression {
    return ExpressionInterpreter.interpret(new Scanner(text));
  }

  /**
   * @param scanner assumed not null.
   * @return null if the command was not recognized.
   */
  public static Expression interpret(final Scanner scanner)
      throws
        InvalidPosition,
        NoSuchSpreadsheet,
        IllegalStartOfExpression,
        InvalidExpression {

    String keyword = null;

    try {
      keyword = scanner.next();
    } catch (NoSuchElementException e) {
      throw new IllegalStartOfExpression();
    }

    //keyword made to lowercase to avoid some user errors
    switch(keyword.toLowerCase()) {
      case "int":
        return new Int(
          scanner.nextInt());
      case "neg":
        return new Neg(
          interpret(scanner));
      case "add":
        return new Add(
          interpret(scanner),
          interpret(scanner));
      case "sum": 
    	  String text = scanner.nextLine();
    	  int indexOfColon = text.indexOf(':');
    	  Position pos1 = PositionInterpreter.interpret(
    			  					text.substring(1, indexOfColon));
    	  Position pos2 = PositionInterpreter.interpret(
    			  					text.substring(indexOfColon+1));
    	  return new Sum(new Reference(Application.instance.getWorksheet(),
    			  		 new Range(pos1, pos2)));
      case "true":
        return new True();
      case "false":
        return new False();
      case "not":
        return new Not(
          interpret(scanner));
      case "and":
        return new And(
          interpret(scanner),
          interpret(scanner));
      case "or":
        return new Or(
          interpret(scanner),
          interpret(scanner));
      case "lt": 
    	return new Lt(
    	  interpret(scanner), 
    	  interpret(scanner));
      case "eq": 
    	return new Eq(
    	  interpret(scanner),
    	  interpret(scanner));
      case "if":
    	  String ifTE = scanner.nextLine();
    	  ifTE.toLowerCase();
    	  int indexThen = ifTE.indexOf("then");
    	  int indexElse = ifTE.indexOf("else");
    	  String thenExp = ifTE.substring(indexThen+5, indexElse);
    	  String elseExp = ifTE.substring(indexElse+5);
    	return new IfThenElse(
    	  interpret(ifTE),
    	  interpret(thenExp),
    	  interpret(elseExp));
      case "text":
        return new Text(scanner.next());
      case "concat":
        return new Concat(
          interpret(scanner),
          interpret(scanner));
      default:
        return interpretReference(keyword);
    }
  }

  /**
   * Interprets the given string as a reference
   * @param text Not fitting any cases of ExpressionInterpreter
   * @return new Reference at the position
   * @throws NoSuchSpreadsheet
   * @throws InvalidPosition
   */
  private static Reference interpretReference(String text)
      throws
        NoSuchSpreadsheet,
        InvalidPosition {

	  Spreadsheet spreadsheet = null;
      final int indexOfBang = text.indexOf('!');
      final int indexOfColon = text.indexOf(':');
      final String text2;
      final String name;
      if (indexOfBang != -1) {
    	  name = text.substring(0, indexOfBang);
      	  spreadsheet = Application.instance.getSpreadsheet(name);
      	  text = text.substring(indexOfBang + 1, text.length());
      }
      else 
    	  spreadsheet = Application.instance.getWorksheet();
      if (indexOfColon != -1) {
          text2 = text.substring(0, indexOfColon);
          text = text.substring(indexOfColon + 1);
          return new Reference(spreadsheet,
                  new Range(PositionInterpreter.interpret(text),
                            PositionInterpreter.interpret(text2)));
      }
      else {
          return new Reference(spreadsheet, PositionInterpreter.interpret(text));
      }

      }
  }