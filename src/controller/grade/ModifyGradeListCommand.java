package controller.grade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import controller.Command;
import model.grade.GradeService;
import model.grade.GradeVo;

public class ModifyGradeListCommand implements Command{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<GradeVo> grades= null;
		GradeService service = GradeService.getInstance();
		ArrayList<Integer> list = service.retrieveGradeNumber();
		
				
		String[] temp1 = request.getParameterValues("gradeNo");
		String[] name = request.getParameterValues("name");
		String[] temp2 = request.getParameterValues("docs");
		String[] temp3 = request.getParameterValues("comms");
		
		int[] gradeNo = Arrays.stream(temp1).mapToInt(Integer::parseInt).toArray();
		int[] docs = Arrays.stream(temp2).mapToInt(Integer::parseInt).toArray();
		int[] comms = Arrays.stream(temp3).mapToInt(Integer::parseInt).toArray();
		
		for(int i = 0; i < name.length; i++) {
			GradeVo grade = new GradeVo();
			grade.setGradeNo(gradeNo[i]);
			grade.setName(name[i]);
			grade.setDocs(docs[i]);
			grade.setComms(comms[i]);
			service.modifyGrade(grade);
		}
		
		ArrayList<Integer> overlap = (ArrayList<Integer>)Arrays.stream(gradeNo).boxed().collect(Collectors.toList());
		
		list.removeAll(overlap);
		
		for(int i=0; i < list.size(); i++) {
			int j = list.get(i).intValue();
			service.removeGrade(j);
		}
		
		grades = service.retrieveGradeList();
		request.setAttribute("grades", grades);
		
		request.setAttribute("content", "viewGradeList.jsp");
		return new ActionForward("/managerIndex.do", false);
	}

}
