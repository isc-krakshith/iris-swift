Class Swift.PEX.FirstDemo.MessageRouterRoutingRule Extends Ens.Rule.Definition
{

Parameter RuleAssistClass = "EnsLib.MsgRouter.RuleAssist";

XData RuleDefinition [ XMLNamespace = "http://www.intersystems.com/rule" ]
{
<ruleDefinition alias="" context="EnsLib.MsgRouter.RoutingEngine" production="Swift.PEX.FirstDemo.Production">
<ruleSet name="" effectiveBegin="" effectiveEnd="">
<rule name="Enviar a BO de PEX">
<constraint name="msgClass" value="Ens.StringRequest"></constraint>
<when condition="1">
<send transform="Swift.PEX.FirstDemo.DT.StringRequest2PEX" target="FirstOperation"></send>
<return></return>
</when>
</rule>
</ruleSet>
</ruleDefinition>
}

}
