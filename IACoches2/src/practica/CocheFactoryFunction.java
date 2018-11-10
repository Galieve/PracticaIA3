package practica;

public class CocheFactoryFunction {
	private static CochesActionFunction _actionsFunction=null;
	private static CocheResultFunction _resultsFunctions=null;
	private static CocheGoalTest _goalTest=null;
	private static CocheStepFunction _stepFunction=null;

	public static CochesActionFunction getActionFunction(){
		if(_actionsFunction==null) {
			_actionsFunction=new CochesActionFunction();
		}
		return _actionsFunction;
	}

	public static CocheResultFunction getResultFunction(){
		if(_resultsFunctions==null) {
			_resultsFunctions=new CocheResultFunction();
		}
		return _resultsFunctions;
	}

	public static CocheGoalTest getGoalTest(){
		if(_goalTest==null) {
			_goalTest=new CocheGoalTest();
		}
		return _goalTest;
	}
	
	public static CocheStepFunction getStepFunction(){
		if(_stepFunction==null) {
			_stepFunction=new CocheStepFunction();
		}
		return _stepFunction;
	}
	
}
