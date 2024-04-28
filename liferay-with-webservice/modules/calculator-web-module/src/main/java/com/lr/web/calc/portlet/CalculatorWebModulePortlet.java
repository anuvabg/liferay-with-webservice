package com.lr.web.calc.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.lr.web.calc.constants.CalculatorWebModulePortletKeys;
import com.lr.wsrest.calcservice.api.CalculatorRestInvokeService;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Anuvab
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CalculatorWebModule",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CalculatorWebModulePortletKeys.CALCULATORWEBMODULE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CalculatorWebModulePortlet extends MVCPortlet {
	private Log _logger = LogFactoryUtil.getLog(CalculatorWebModulePortlet.class);
	@Reference
	private CalculatorRestInvokeService calculatorService;
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		_logger.info(" CalculatorWebModulePortlet : render is invoked. ");
		String param1 = "10";
		String param2 = "8";
		
		String result = calculatorService.getCalculation(CalculatorWebModulePortletKeys.CALC_OPERATION_SUM, param1, param2);
		_logger.info(" CalculatorWebModulePortlet : render : result : " + result);
		renderRequest.setAttribute("result", result);
		super.render(renderRequest, renderResponse);
	}
}