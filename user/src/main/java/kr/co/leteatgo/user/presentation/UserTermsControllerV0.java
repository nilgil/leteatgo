package kr.co.leteatgo.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@Tag(name = "User Terms", description = "v0")
@RequiredArgsConstructor
@RequestMapping("/terms")
@RestController
public class UserTermsControllerV0 {

    @Operation(summary = "가입 약관 전체 조회")
    @GetMapping
    public Map<String, String> getTERMS() {
        return TERMS;
    }

    private static final Map<String, String> TERMS;

    static {
        TERMS = new HashMap<>();
        TERMS.put("serviceUse", "제1조 (목적)\n" +
                "본 약관은 주식회사 와이낫어스(이하 “회사”라 함)가 운영하는 렛잇고 서비스(이하 “서비스”라 함)를 이용함에 있어 이용조건 및 절차, 회사와 회원간의 권리, 의무 및 기타 필요한 사항을 규정함을 목적으로 합니다.\n" +
                "\n" +
                "제2조 (용어의 정의)\n" +
                "① “앱”이란 회사가 취득한 판매자 정보 또는 서비스 상품(이하 “상품”이라 합니다)을 “이용자”에게 정보 제공을 위하여, “회사”가 컴퓨터 등 정보통신설비를 이용하여 상품을 찾아볼 수 있게 설정하여 ”서비스”를 제공하는 모바일 어플리케이션의 가상의 영업장을 말합니다.\n" +
                "\n" +
                "② “서비스”란 회사가 운영하는 앱을 통해 이용자가 가격 또는 상품 등의 정보를 취득하는 서비스 일체를 의미합니다. \n" +
                "\n" +
                "③ “이용자”라 함은 본 약관에 따라 렛잇고의 서비스를 이용하는 자를 말하며, 회원가입 한 자만 해당 됩니다.\n" +
                "\n" +
                "④ “회원”이라 함은 서비스 회원가입 약관에 동의하고 개인정보를 제공하여 회원가입을 한 자로서, 회사가 제공하는 서비스를 계속적으로 이용할 수 있는 자를 의미합니다.\n" +
                "\n" +
                "⑤ “비회원”이라 함은 “회원”으로 가입하지 않은 자를 말하며, “서비스” 이용이 불가합니다.\n" +
                "\n" +
                "⑥ 본 약관에서 정의되지 않은 용어는 관련 법령이 정하는 바에 따릅니다.\n" +
                "\n" +
                "제3조 (약관의 명시와 개정)\n" +
                "① 회사는 본 약관의 내용과 상호명, 영업소 소재지 주소(소비자의 불만을 처리할 수 있는 곳의 주소를 포함), 대표자의 성명, 사업자등록번호, 통신판매업 신고번호, 연락처(전화, 전자우편 주소 등) 등을 이용자가 쉽게 알 수 있도록 앱의 초기 서비스화면에 게시합니다. 다만, 약관의 내용은 이용자가 연결화면을 통하여 보도록 할 수 있습니다.\n" +
                "\n" +
                "② 회사는 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 앱의 초기화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. 다만, 이용자에게 불리하게 약관내용을 변경하는 경우에는 최소한 30일 이상의 사전 유예기간을 두고 공지합니다. 이 경우 회사는 개정 전 내용과 개정 후 내용을 명확하게 비교하여 이용자가 알기 쉽도록 표시합니다.\n" +
                "\n" +
                "③ 회원은 변경된 약관에 동의하지 않을 권리가 있으며, 변경된 약관에 동의하지 않을 경우에는 서비스 이용을 중단하고 탈퇴를 요청할 수 있습니다. 다만, 전항의 방법에 따라 회사가 별도 고지한 약관의 개정 공지 기간 내에 회원이 회사에 개정 약관에 동의하지 않는다는 명시적인 의사표시를 하지 않는 경우 변경된 약관에 동의한 것으로 간주합니다.\n" +
                "\n" +
                "제4조 (서비스의 제공)\n" +
                "① 회사는 다음과 같은 서비스를 제공합니다\n" +
                "1. 상품에 대한 정보 제공 및 주문 유도 등 정보 제공 및 주문 유도 서비스\n" +
                "2. 상품 또는 관련 용역에 대한 정보 제공 \n" +
                "\n" +
                "제5조 (서비스 이용시간 및 중단)\n" +
                "① 서비스의 이용은 회사의 업무상 또는 기술상 특별한 지장이 없는 한 연중무휴 1일 24시간을 원칙으로 합니다. 다만, 다음 각 호에 해당하는 사유가 발생하는 경우에는 필요한 기간을 정하여 사전에 공지하고 서비스 이용을 제한하거나 중지할 수 있습니다. 단, 불가피하게 긴급한 조치를 필요로 하는 경우 사후에 통지할 수 있습니다.\n" +
                "1. 컴퓨터 등 정보통신설비의 보수점검·교체 및 고장\n" +
                "2. 「전기통신사업법」에 규정된 기간통신사업자가 전기통신 서비스를 중지하는 등 통신의 두절\n" +
                "3. 국가비상사태, 서비스 설비의 장애 또는 서비스 이용의 폭주 등으로 서비스 이용에 지장이 있는 경우\n" +
                "4. 기타 위 각 호에 준하는 사유로 인하여 회사가 정상적인 서비스를 제공하는 것이 어렵다는 점이 객관적으로 인정되는 경우\n" +
                "\n" +
                "② 회사는 제 1항의 사유로 서비스의 제공이 일시적으로 중지됨으로 인하여 이용자 또는 제3자가 입은 손해에 대하여 배상합니다. 단, 회사의 고의 또는 중대한 과실이 없는 경우에는 그러하지 아니합니다.\n" +
                "\n" +
                "③ 회사는 서비스 이용시간 중 판매자의 영업종료, 배달원의 부재 등 사유로 인하여 상품의 판매가 불가능한 경우 그로 인한 회원의 손해를 배상할 책임을 지지 않습니다.\n" +
                "\n" +
                "제6조 (서비스 가입)\n" +
                "① 이용자가 본 약관에 동의 하고, 회사가 정한 가입 양식에 따라 회원정보를 기입하여 회원가입 신청을 하고 회사가 이러한 신청에 대하여 승인함으로써 서비스 이용계약이 체결됩니다.\n" +
                "\n" +
                "② 회사는 다음 각 호에 해당하는 신청에 대하여는 승인을 하지 않을 수 있습니다.\n" +
                "1. 회사가 본 약관 제7조 제3항에 따라 해당 이용자의 회원자격이 정지한 적이 있는 경우. 다만, 회원자격 정지 후 3년이 경과한 자로서 회사의 회원 재가입 승낙을 얻은 경우에는 예외로 함\n" +
                "2. 타인의 명의를 이용한 경우\n" +
                "3. 회사가 관계 법령에서 요구하는 바에 따라 실명확인을 실시할 때 실명이 아님이 확인된 경우\n" +
                "4. 등록내용에 허위의 정보를 기재하거나, 필수기재 사항을 기재하지 않은 경우\n" +
                "5. 이미 가입된 회원과 휴대전화번호나 전자우편주소가 동일한 경우\n" +
                "6. 부정한 목적으로 서비스를 이용하고자 함이 명백한 경우\n" +
                "7. 회원가입 신청일 기준 만 14세 미만인 경우\n" +
                "8. 기타 본 약관에 위배되거나 위법 또는 부당한 이용신청임이 확인된 경우 및 이에 준하는 사유가 발생한 경우\n" +
                "\n" +
                "③ 회사는 제①항에 따른 회원가입신청을 한 이용자에게 인증기관을 통한 본인확인을 요청할 수 있으며, 관계법령에 따라 실명확인 또는 고객확인을 요청할 수 있습니다.\n" +
                "\n" +
                "④ 회사는 서비스관련설비 등에 기술상 또는 업무상 문제가 있는 경우에는 승낙을 유보할 수 있으나 관련 문제가 해결되는 경우 지체 없이 승인 여부를 심사하여 결정합니다.\n" +
                "\n" +
                "⑤ 회원은 회원가입 시 등록한 사항에 변경이 있는 경우, 상당한 기간 이내에 회사에 대하여 회원정보 수정 등의 방법으로 그 변경사항을 알려야 합니다. 회원이 변경사항을 알리지 않아 발생하는 손해에 관하여 회사는 회사의 고의 또는 중대한 과실이 없는 한 배상할 책임이 없습니다.\n" +
                "\n" +
                "제7조 (서비스 해지)\n" +
                "① 회원은 언제든지 회사에게 해지의사를 통지함으로써 이용계약을 해지할 수 있으며 회사는 회원의 해지요청에 대해 특별한 사정이 없는 한 이를 즉시 처리합니다.\n" +
                "\n" +
                "② 회사는 다음과 같은 사유가 있는 경우 제③항의 절차에 따라 회원의 자격을 정지할 수 있습니다.\n" +
                "1. 제6조 제②항에서 정하고 있는 이용계약의 승낙거부사유가 있음이 확인된 경우\n" +
                "2. 타인에게 피해를 주거나 기타 미풍양속을 현저히 저해하는 행위를 한 경우\n" +
                "3. 회원이 회사나 다른 회원, 기타 타인의 권리나 명예, 신용 기타 정당한 이익을 침해하는 행위를 하였다고 객관적으로 인정되는 경우\n" +
                "4. 기타 회원이 본 약관에 위배되는 행위를 하는 경우\n" +
                "\n" +
                "③ 회사는 전항의 사유가 발생하는 경우 원칙적으로 회원에게 사전 고지 후 자격을 정지할 수 있습니다. 다만, 예외적으로 법령상 의무를 준수하기 위한 경우이거나, 이용자, 제3자 및 회사의 중대한 손해를 방지하기 위하여 불가피한 경우에는 예외적으로 사전 고지 없이 자격을 정지할 수 있으나, 이 경우에도 해당 회원에게 지체없이 그 사실을 제11조(회원에 대한 통지)에서 규정한 방법으로 개별 통지를 합니다.\n" +
                "\n" +
                "④ 해지를 통지 받은 회원이 해지에 이의를 제기하고자 하는 경우 회사의 고객센터에 문의할 수 있습니다.\n" +
                "\n" +
                "제8조 (회원 및 이용자의 의무)\n" +
                "① 이용자는 관계법령 및 본 약관을 준수하여야 하며, 기타 회사 업무에 방해되는 행위를 하여서는 안됩니다.\n" +
                "\n" +
                "② 이용자는 서비스 이용과 관련하여 다음 각 호의 행위를 하여서는 안됩니다. 이 때, 각 호의 행위에 관한 구체적인 내용의 해석에 대해서는 회사의 정책, 이용안내 등에 따릅니다.\n" +
                "1. 서비스 신청 또는 변경 시 허위내용의 등록\n" +
                "2. 타인의 명의나 정보를 도용\n" +
                "3. 회사에 게시된 정보의 허가 받지 않은 변경\n" +
                "4. 회사가 정한 정보 이외의 정보(컴퓨터 프로그램 등)의 송신 또는 게시\n" +
                "5. 회사 또는 제3자의 저작권 등 지적 재산권에 대한 침해\n" +
                "6. 회사 또는 제3자의 명예를 손상시키거나 업무를 방해하는 행위\n" +
                "7. 외설 또는 폭력적인 메시지, 화상, 음성 기타 공공질서 미풍양속에 반하는 정보를 서비스에 공개 또는 게시하는 행위\n" +
                "8. 고객센터 상담 시 욕설, 폭언, 성희롱 등을 하는 행위\n" +
                "9. 자신의 ID, PW를 제3자에게 양도하거나 대여하는 등의 행위\n" +
                "10. 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」등 관련 법령을 위반한 불법통신 및 해킹, 악성프로그램의 배포 등 비정상적 이용 행위를 통하여 서비스를 복제, 분해 또는 모방 기타 변형하는 행위\n" +
                "11. 자동 접속 프로그램 등을 사용하는 등 정상적인 용법과 다른 방법으로 서비스를 이용하여 회사의 서버에 부하를 일으켜 회사의 정상적인 서비스를 방해하는 행위\n" +
                "12. 기타 관계법령에 위반된다고 판단되는 행위\n" +
                "\n" +
                "제9조 (이용제한 등)\n" +
                "① 회사는 이용자가 본 약관의 의무를 위반하거나 서비스의 정상적인 운영을 방해한 경우, 이용자에게 사전통지한 후 주의, 경고, 일시정지, 영구 이용정지, 계약해지 등의 조치를 취할 수 있으며, 이용자는 법적책임을 부담합니다. 다만, 불가피하게 긴급한 조치를 필요로 하는 경우 이용제한 조치를 취한 직후에 통지할 수 있습니다.\n" +
                "\n" +
                "② 회사는 제8조 제②항의 금지행위와 관련하여 「주민등록법」, 「저작권법」, 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」, 「여신전문금융업법」 등 관련법을 위반한 경우에는 일시정지, 영구이용정지, 계약해지 등의 조치를 즉시 취할 수 있으며, 이용자는 법적책임을 부담할 수 있습니다.\n" +
                "\n" +
                "③ 회사는 회원이 계속해서 1년 이상 로그인하지 않는 경우, 회원정보의 보호를 위하여 파기 또는 별도 분리·보관할 수 있습니다.\n" +
                "\n" +
                "④ 본 조의 이용제한 범위 내에서 제한의 조건 및 세부내용은 회사의 이용제한정책에서 정하는 바에 의합니다.\n" +
                "\n" +
                "⑤ 본 조에 따라 서비스 이용을 제한하거나 계약을 해지하는 경우에는 회사는 제11조(회원에 대한 통지)에서 규정한 방법으로 개별 통지를 합니다.\n" +
                "\n" +
                "⑥ 회원은 본 조에 따른 이용제한 등에 대해 회사가 정한 절차에 따라 이의신청을 할 수 있습니다. 이 때 이의가 정당하다고 회사가 인정하는 경우 회사는 즉시 서비스의 이용을 재개합니다.\n" +
                "\n" +
                "⑦ 본 조에 따라 이용제한이 되는 경우 서비스 이용을 통해 획득한 혜택 등도 모두 이용중단, 또는 소멸되며, 회사는 이에 대해 별도로 보상하지 않습니다.\n" +
                "\n" +
                "제10조 (권리의 귀속)\n" +
                "① 서비스에 대한 저작권 및 지적재산권은 회사에 귀속됩니다. 단, 회원의 게시물 및 제휴계약에 따라 제공된 저작물 등은 제외합니다.\n" +
                "\n" +
                "② 회사가 제공하는 서비스의 디자인, 회사가 만든 텍스트, 스크립트(script), 그래픽, 회원 상호간 전송 기능 등 회사가 제공하는 서비스에 관련된 모든 상표, 서비스 마크, 로고 등에 관한 저작권 기타 지적재산권은 대한민국 및 외국의 법령에 기하여 회사가 보유하고 있거나 회사에게 소유권 또는 사용권이 있습니다.\n" +
                "\n" +
                "③ 회원은 본 약관으로 인하여 서비스를 소유하거나 서비스에 관한 저작권을 보유하게 되는 것이 아니라, 회사로부터 서비스의 이용을 허락 받게 되는바, 정보취득 또는 개인용도로만 서비스를 이용할 수 있습니다.\n" +
                "\n" +
                "제11조 (회원에 대한 통지)\n" +
                "① 회사가 회원에 대한 통지를 하는 경우, 회원이 가입신청 시 회사에 제출한 휴대전화번호 등으로 할 수 있습니다.\n" +
                "\n" +
                "② 회사는 불특정다수 회원에 대한 통지의 경우, 1주일 이상 앱에 게시함으로써 개별 통지에 갈음할 수 있습니다. 다만 회원 본인의 거래와 관련하여 중대한 영향을 미치는 사항에 대하여는 전항에서 규정한 방법으로 개별 통지를 합니다.\n" +
                "\n" +
                "제12조 (회사의 의무)\n" +
                "① 회사는 관련법과 본 약관이 금지하거나 미풍양속에 반하는 행위를 하지 않으며, 계속적이고 안정적으로 서비스를 제공하기 위하여 최선을 다하여 노력합니다.\n" +
                "\n" +
                "② 회사는 회원이 안전하게 서비스를 이용할 수 있도록 개인정보(신용정보 포함) 보호를 위해 개인정보처리방침을 수립하여 공시하고 준수합니다.\n" +
                "\n" +
                "③ 회사는 관계 법령이 정한 의무사항을 준수합니다.\n" +
                "\n" +
                "제13조 (개별 서비스에 대한 약관)\n" +
                "① 회사는 개별 서비스와 관련한 별도의 약관 및 이용정책을 둘 수 있으며, 개별서비스에서 별도로 적용되는 약관에 대한 동의는 회원이 개별서비스를 최초로 이용할 경우 별도의 동의절차를 거치게 됩니다. 이 경우 개별 서비스에 대한 이용약관 등이 본 약관에 우선합니다.\n" +
                "\n" +
                "② 전항에도 불구하고 회사는 개별 서비스에 대한 이용정책에 대해서는 서비스를 통해 공지할 수 있으며, 이용자는 이용정책을 숙지하고 준수하여야 합니다.\n" +
                "\n" +
                "제14조 (개인정보보호)\n" +
                "”렛잇고”는 이용자의 개인정보 보호를 위하여 별도의 개인정보처리방침을 마련하였으므로, 구체적인 내용은 해당 개인정보처리방침을 확인하여 주시기 바랍니다.\n" +
                "\n" +
                "제15조 (정보의 제공 및 광고의 게재)\n" +
                "① 회사는 서비스의 이용에 관한 정보를 공지사항이나 제11조(회원에 대한 통지)의 방법으로 회원에게 제공할 수 있습니다.\n" +
                "\n" +
                "② 회원은 관련 법령에 따른 경우를 제외하고는 언제든지 전항의 정보 제공에 대해서 수신을 거절을 할 수 있으며, 이 경우 회사는 즉시 정보 제공 행위를 중단합니다.\n" +
                "\n" +
                "③ 다만 회원이 전항에 의해 정보 수신을 거절한 경우, 회사는 서비스 이용에 필수적으로 요구되는 정보(예 : 관련 규정/정책의 변경 등)를 서비스의 공지화면에 공시하는 방법으로 정보의 제공을 갈음할 수 있으며, 회원이 수신을 거부하고 공시된 정보의 제공을 확인하지 아니함에 따라 발생하는 손해에 대해서 회사는 책임을 지지 않습니다.\n" +
                "\n" +
                "④ 회사는 회원이 마케팅 정보 수신동의를 한 경우, 다양한 광고(마케팅) 정보를 공지사항이나 제11조(회원에 대한 통지)의 방법으로 제공할 수 있습니다. 이 경우 회원의 통신환경 또는 요금구조 등에 따라 회원이 데이터요금 등을 부담할 수 있습니다.\n" +
                "\n" +
                "⑤ 회원은 언제든지 전항의 광고(마케팅) 정보 수신을 거절을 할 수 있으며, 이 경우 회사는 즉시 광고(마케팅) 정보 제공 행위를 중단합니다.\n" +
                "\n" +
                "⑥ 정보의 제공 및 광고의 게재에 대한 거절은 ‘회원’이 직접 고객센터에 문의를 주어야 합니다.\n" +
                "\n" +
                "제16조 (책임의 제한)\n" +
                "① 회사는 고의 또는 중대한 과실이 없는 한 다음 각 호의 사항이나 그로 인하여 회원에게 발생한 손해에 대하여 책임을 지지 않습니다.\n" +
                "1. 전시, 사변 등 국가 비상사태 및 천재지변 기타 이에 준하는 불가항력인 상황으로 인하여 서비스를 제공할 수 없는 경우\n" +
                "2. 기간통신사업자 등 전기통신사업자가 전기통신 서비스를 중지하거나 정상적으로 제공하지 아니하여 손해가 발생한 경우\n" +
                "3. 사전에 공지된 서비스용 설비의 보수, 교체, 정기점검, 공사 등 부득이한 사유로 서비스가 중지되거나 장애가 발생한 경우\n" +
                "4. 회원 정보의 허위 또는 부정확성, 서비스 접속 및 이용과정 등에서의 회원의 귀책사유로 인한 서비스 이용의 중지, 장애 또는 손해가 발생한 경우\n" +
                "5. 서버에 대한 제3자의 모든 불법적인 접속 또는 서버의 불법적인 이용으로 인해 발생한 손해 및 제3자의 불법적인 행위를 방지하거나 예방하는 과정에서 발생하는 손해의 경우\n" +
                "6. 제3자가 서비스를 이용하여 불법적으로 전송, 유포하거나 또는 전송, 유포되도록 한 모든 바이러스, 스파이웨어 및 기타 악성 프로그램으로 인해 손해가 발생하는 경우\n" +
                "\n" +
                "② 회사는 회원 및 사업자가 서비스에 게재한 게시물 등 각종 정보의 신뢰도, 정확성 등 내용에 대하여 회사의 고의 또는 중대한 과실이 없는 한 책임을 부담하지 않습니다.\n" +
                "\n" +
                "③ 회사는 회원간 또는 회원과 제3자 상호간에 서비스를 매개로 하여 거래 등을 한 경우에는 책임이 면제됩니다.\n" +
                "\n" +
                "제17조 (손해배상)\n" +
                "① 회사가 법률 및 본 약관을 위반한 행위로 인하여 이용자에게 손해가 발생한 경우 이용자는 회사에 대하여 손해배상 청구를 할 수 있습니다. 이 경우 회사는 고의 또는 중대한 과실이 없음을 입증하지 못 하는 경우 책임을 면할 수 없습니다.\n" +
                "\n" +
                "② 회사는 이용자에게 상품 정보 제공 서비스를 제공할 뿐이므로, 이용자가 구매하신 상품의 품질이나 상품의 신뢰도에 대해서는 보증하지 않습니다.\n" +
                "\n" +
                "③ 회사는 이용자가 구매한 상품에 대해 보증하거나 별도의 책임을 지지 않으며, 회사의 귀책사유가 없는 한 상품과 관련한 일체의 책임이 회사에 없음을 알려드립니다.\n" +
                "\n" +
                "④ 회사에 손해배상을 청구하는 경우에는 청구사유, 청구금액 및 산출근거를 기재하여 서면으로 제출하여야 합니다.\n" +
                "\n" +
                "제18조 (분쟁해결)\n" +
                "① 회사는 이용자가 제기하는 정당한 의견이나 불만을 반영하고 그 피해를 보상처리하기 위하여 고객상담 및 피해보상처리기구를 설치·운영합니다.\n" +
                "\n" +
                "② 회사는 이용자로부터 제출되는 불만사항 및 의견에 대해 우선적으로 그 사항을 처리합니다. 다만, 신속한 처리가 곤란한 경우에는 이용자에게 그 사유와 처리일정을 즉시 통보해드립니다.\n" +
                "\n" +
                "③ 이용자는 서비스에 관하여 이의가 있을 때 회사에 서면, 고객센터 또는 인터넷 등을 통하여 그 해결을 요청할 수 있습니다.\n" +
                "\n" +
                "④ 이용자가 회사에 이의를 제기한 경우 회사는 분쟁처리 책임자, 담당자 및 연락처를 휴대폰 메시지, 이메일 등의 방법으로 이용자에게 통지하며 영업일 14일이내에 이에 대한 조사 또는 처리결과를 이용자에게 안내합니다.\n" +
                "\n" +
                "⑤ 회사는 위치정보와 관련된 분쟁에 대해 당사자간 협의가 이루어지지 아니하거나 협의를 할 수 없는 경우에는 개인정보분쟁조정위원회에 조정을 신청하거나 방송통신위원회에 재정을 신청할 수 있습니다.\n" +
                "\n" +
                "제19조 (준거법 및 관할법원)\n" +
                "① 본 약관의 해석 및 회사와 회원간의 분쟁에 대하여는 대한민국의 법을 적용합니다.\n" +
                "\n" +
                "② 본 약관에 명시되지 않은 사항 및 양 당사자의 해석이 다른 사항에 대하여는 상법 기타 상관례를 바탕으로 한 상호 합의에 따라 해결함을 원칙으로 하며, 원만한 해결이 이루어지지 않을 경우 민사소송법에 따른 관할법원에서 분쟁을 해결합니다.\n" +
                "\n" +
                "<부칙>");

        TERMS.put("collectPersonalInfo", "주식회사 와이낫어스(이하 “회사”라고 합니다.)는 렛잇고 음식점 정보 제공 플랫폼에서 제공하는 서비스(이하 “서비스”라고 합니다.)와 관련하여 개인정보보호를 위하여 개인정보 보호법 등 관련 법령을 철저히 준수하며, 본 개인정보 처리방침을 따릅니다.\n" +
                "\n" +
                "본 개인정보 처리방침의 목차는 아래와 같습니다.\n" +
                "\n" +
                "1. 개인정보 처리 목적\n" +
                "2. 개인정보 처리방침의 의의\n" +
                "3. 개인정보 수집·이용, 보유 및 이용기간\n" +
                "4. 개인정보 수집 출처 등 고지\n" +
                "5. 개인정보의 제3자 제공\n" +
                "6. 개인정보 처리 위탁\n" +
                "7. 개인정보의 파기절차 및 방법\n" +
                "8. 이용자 및 법정대리인의 권리와 그 행사 방법\n" +
                "9. 개인정보 자동 수집 설치/운영 및 거부에 관한 사항\n" +
                "10. 개인정보 보호책임자 및 고충사항 처리 부서\n" +
                "\n" +
                "1.개인정보 처리 목적\n" +
                "회사는 렛잇고 음식점 정보 제공 플랫폼에서 서비스를 제공하기 위하여 필요 최소한의 개인정보만을 수집합니다. 회사가 수집한 개인정보는 다음의 목적을 위해 이용됩니다. 이용자가 제공한 정보는 다음의 목적 이외로는 이용되지 않으며, 이용목적이 변경될 시에는 사전동의 등 필요한 조치를 구할 것입니다.\n" +
                "\n" +
                "가. 회원 가입 의사 확인 및 서비스 제공, 본인인증, 실명인증, 이용자 식별 및 관리\n" +
                "나. 서비스 제공 및 관리(음식점 정보 제공), 서비스 개선, 신규 서비스 개발\n" +
                "다. 메뉴추천서비스 제공 및 고도화\n" +
                "라. 불법 및 부정 이용방지, 부정 사용자 차단 및 관리\n" +
                "마. 통계학적 분석(이용자의 서비스 이용에 대한 통계분석, 웹로그 분석 등)\n" +
                "바. 서비스 만족도 조사 및 관리\n" +
                "사. 맞춤서비스, 개인화 서비스 제공\n" +
                "아. 보안정책 수립, 온라인 정보 도용 및 금융사고 예방, 사고조사, 민원 처리 등의 목적\n" +
                "자. 광고성 정보 제공 등 마케팅 및 프로모션, 공지사항 전달, 본인 의사 확인, SMS문자서비스 가입 확인, 불만 처리 등 원활한 의사소통 경로의 확보, 분쟁조정을 위한 기록보존 등\n" +
                "\n" +
                "2. 개인정보 처리방침의 의의\n" +
                "회사는 서비스 이용 및 제공을 위한 최소한의 정보를 수집하고, 수집한 정보는 고지된 범위 내에서만 이용하며, 사전 동의 없이 그 범위를 초과하여 이용하거나 외부에 제공하지 않습니다.\n" +
                "또한, 개인정보 처리방침이 변경되었을 때는 정보주체에게 통지하고, 통지된 내용을 어플리케이션, 렛잇고 노션 페이지, 브랜드 사이트 상의 공지사항에 게시할 것입니다.\n" +
                "본 개인정보 처리방침은 회사가 이용자의 개인정보를 처리함에 있어, 관련 법령에 따라 준수해야 할 회사의 정책을 반영한 것입니다.\n" +
                "\n" +
                "3. 개인정보 수집·이용, 보유 및 이용기간\n" +
                "회사는 이용하는 서비스의 형태에 따라 다음과 같이 개인정보를 수집·이용합니다. “7. 개인정보의 파기절차 및 방법”에서 언급한 바와 같이 회사 정책에 따른 정보보유사유가 발생하여 보존할 필요가 있는 경우에는 정책에 따라 보관합니다.\n" +
                "회사는 서비스 이용을 위해 아래와 같이 정보를 수집·이용합니다.\n" +
                "\n" +
                "가.회원\n" +
                "필수: 회원가입 / 수집,이용 목적: 회원가입 의사확인, 본인인증, 이용자 식별 및 관리 / 수집,이용항목: 휴대전화번호, CI, 제 3자로부터 제공받는 정보 휴대전화 본인(인증기관 : CI) / 보유 및 이용기간: 회원탈퇴시(관계법령에 따라 보관되는 정보는 예외로 처리되며 회원가입 남용 및 서비스 부정 사용에 대한 확인 및 방지를 위해 회원가입시 수집한 정보는 회원탈퇴 후 5년 후 파기)\n" +
                "선택: 이벤트 응모 및 경품 발송 시 / 수집,이용 목적: 서비스 제공 및 관리 / 수집, 이용항목: 성명, 닉네임, 휴대전화번호, 이메일 주소(진행되는 이벤트에 따라 수집하는 항목은 상이할 수 있음)  / 보유 및 이용기간: 회원 탈퇴시\n" +
                "\n" +
                "4.개인정보 수집 출처 등 고지\n" +
                "가. 회사는 정보주체 이외로부터 수집한 개인정보를 처리하는 때에는 정당한 사유가 없는 한 정보주체의 요구가 있는 날로부터 3일 이내에 수집 출처, 처리 목적, 개인정보 처리의 정지를 요구할 권리가 있다는 사실을 정보주체에게 알립니다.\n" +
                "나. 개인정보 보호법 제20조 제2항 각호에 근거하여 제1항에 따른 정보주체의 요구를 거부하는 경우에는 정당한 사유가 없는 한 정보주체의 요구가 있는 날로부터 3일 이내에 그 거부의 근거와 사유를 정보주체에게 알립니다.\n" +
                "\n" +
                "5.개인정보의 제3자 제공\n" +
                "회사는 수집한 개인정보를 회원이 동의한 목적 범위 내에서 사용하며, 회원의 동의 없이 또는 동의 범위를 초과하여 이용하거나 외부에 공개·제공하지 않습니다. 아래와 같은 서비스 제공을 위해 회원의 개인정보를 제3자에게 제공할 필요할 경우가 있는 경우에는 회원의 동의를 받아 최소한의 범위 내에서 개인정보를 제공합니다.\n" +
                "\n" +
                "단, 다른 법률에 특별한 규정이 있거나 혹은 관련 법령에 따라 보관·제공되어야하는 경우 개인정보를 제3자에게 제공할 수 있습니다. 다만, 회사는 법률에 특별한 규정이 있거나 관련 법령에 의거 요구되는 경우(예: 적법한 절차에 의한 조사기관 및 수사기관 등의 요청이 있는 경우 등)에는 예외로써 개인정보를 제3자에게 제공할 수 있습니다.\n" +
                "\n" +
                "6.개인정보의 처리위탁\n" +
                "① 회사는 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리 업무를 위탁하고 있습니다.\n" +
                "\n" +
                "수탁업체/위탁업무 내용\n" +
                "\n" +
                "(주)네이버클라우드/핸드폰 인증\n" +
                "\n" +
                "②회사는 다음과 같이 개인정보를 국내로 이전하고 있습니다.\n" +
                "\n" +
                "이전받는 자\n" +
                "Amazon Web Service Inc.\n" +
                "(aws-korea-privacy@amazon.com)\n" +
                "\n" +
                "Braze Inc.\n" +
                "(privacy@braze.com)\n" +
                "\n" +
                "국가 대한민국(서울 리전)\n" +
                "\n" +
                "이전되는 개인정보 항목: 서비스 이용 시 수집하는 정보, 서비스 이용 정보, 방문 기록, 기기정보\n" +
                "이전일시 및 이전방법: 서비스 이용 시 네트워크를 통한 이전, 서비스 이용 시 네트워크를 통한 이전\n" +
                "이용목적 및 보유기간: 데이터 저장 및 인프라 운영\n" +
                "회원탈퇴일로부터 90일까지, 맞춤 푸시/인앱메시지 발송\n" +
                "위탁 계약 종료 시 까지\n" +
                "\n" +
                "③ 회사는 위탁계약 체결 시 위탁업무 수행 목적 외 개인정보 처리 금지, 기술적 ∙ 관리적 보호조치, 재 위탁 제한, 수탁자에 대한 관리 ∙ 감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를 안전하게 처리하는지를 감독하고 있습니다.\n" +
                "④ 위탁 업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.\n" +
                "\n" +
                "7.개인정보의 파기절차 및 방법\n" +
                "회원 탈퇴, 서비스 종료, 이용자에게 동의 받은 개인정보 보유기간의 도래와 같이 개인정보의 수집 및 이용목적이 달성된 개인정보를 재생이 불가능한 방법으로 파기하고 있습니다.\n" +
                "법령에서 보존의무를 부과한 정보에 대해서도 해당 기간 경과 후 지체 없이 재생이 불가능한 방법으로 파기합니다.\n" +
                "전자적 파일 형태의 경우 복구 및 재생이 되지 않도록 기술적인 방법을 이용하여 안전하게 삭제하며, 출력물 등은 분쇄하거나 소각하는 방식 등으로 파기합니다.\n" +
                "참고로 회사는 ‘개인정보 유효기간제’에 따라 1년간 서비스를 이용하지 않은 회원 또는 판매자의 개인정보를 별도로 분리 보관 또는 삭제하여 관리하고 있습니다. 단, 아래의 정보에 대해서는 아래 기재된 사유로 명시한 기간 동안 보관합니다.\n" +
                "\n" +
                "가. 회사 정책에 따른 정보보관 사유\n" +
                "회사는 회원 가입 남용 및 서비스 부정 사용에 대한 확인 및 방지를 위해 성명, 휴대전화번호, CI, 성별, 생년월일, 이메일주소, 내/외국인 여부는 회원 탈퇴 또는 1년 이상 서비스를 이용하지 아니한 회원의 경우 위 정보의 분리 보관 후 5년이 지나면 파기합니다.\n" +
                "\n" +
                "8.이용자 및 법정대리인의 권리와 그 행사 방법\n" +
                "가. 이용자는 언제든지 어플리케이션 내에서 자신의 개인정보를 조회하거나 수정할 수 있으며, 자신의 개인정보에 대한 개인정보의 열람을 요청할 수 있습니다.\n" +
                "나. 본인의 개인정보를 열람한 이용자는 사실과 다르거나 확인할 수 없는 개인정보에 대하여 회사에 정정 또는 삭제를 요구할 수 있습니다. 다만, 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.\n" +
                "다. 이용자는 당행에 대하여 자신의 개인정보 처리의 정지를 요구할 수 있습니다. 다만 다음 각 호의 어느 하나에 해당하는 경우에는 회사는 해당 사유를 이용자에게 알리고, 처리정지 요구를 거절할 수 있습니다. 본인의 개인정보를 열람한 고객은 사실과 다르거나 확인할 수 없는 개인정보에 대하여 회사에 정정 또는 삭제를 요구할 수 있습니다.\n" +
                "특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우\n" +
                "다른 사람의 생명·신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우\n" +
                "개인정보를 처리하지 아니하면 회사가 서비스를 제공하지 못하는 등 계약의 이양이 곤란한 경우로서 이용자가 그 계약의 해지의사를 명확하게 밝히지 아니한 경우\n" +
                "라. 이용자의 권리는 인터넷 홈페이지 또는 앱 화면에서 직접 처리하거나 ‘문의하기’를 통해 행사할 수 있습니다. \n" +
                "\n" +
                "9.자동 수집 설치/운영 및 거부에 관한 사항\n" +
                "가. 회사는 이용자에게 최적화된 맞춤형 상품·서비스 및 혜택을 안내하기 위해 행태정보를 수집·이용하고 있습니다. 행태정보란 앱 사용이력, 구매 및 검색 이력 등 고객 및 회원의 관심, 성향 등을 파악하고 분석할 수 있는 온라인 상의 이용자 활동정보를 말합니다. 행태정보는 서비스 이용과정에서 자동으로 수집 및 저장되며, 개인정보와 결합되지 않고, 개인식별이 불가능한 상태로 활용됩니다. 아울러 회사가 수집하는 행태정보는 분석업체와 제휴광고사업자에 식별이 불가능한 상태로 제공될 수 있습니다.\n" +
                "\n" +
                "1. 수집하는 행태정보의 항목\n" +
                "이용자의 앱 서비스 내 방문기록, 검색·클릭 등의 사용기록, 광고식별자\n" +
                "2. 행태정보 수집방법\n" +
                "이용자가 앱 내에서 행해지는 주요행동에 대해 생성 정보 수집 툴(구글애널리틱스 등)을 통해 자동 수집 전송\n" +
                "구글, 카카오는 앱 이용자 행태정보를 수집·제공받습니다. 이용자 통제권 행사 방법을 통해 맞춤형 광고를 거부할 수 있습니다. 단 설정을 하시더라도 일반적인 상품 광고는 노출될 수 있습니다.\n" +
                "① 행태정보를 전달 받는 자\n" +
                "구글, 카카오\n" +
                "② 행태정보 수집 목적\n" +
                "회사는 다음과 같은 목적으로 이용자들의 행태정보를 이용할 수 있습니다.\n" +
                "고객이용 통계를 활용한 신상품·서비스 개발, 사용자 행태정보 기반 맞춤형 광고 제공\n" +
                "③ 행태정보 보유 이용기간 및 이후 정보처리 방법\n" +
                "수집일로부터 최대 26개월간 보유·이용되며, 보유기간 경과 후에는 월 단위로 자동 삭제됩니다.\n" +
                "④ 이용자 통제권 행사방법\n" +
                "가) 안드로이드폰\n" +
                "설정 → 계정 → 구글 계정 선택 → 광고 선택 → 광고 맞춤설정 선택 해제\n" +
                "(OS버전에 따라 다소 상이할 수 있음)\n" +
                "나) 아이폰\n" +
                "설정 → 개인정보보호 선택 → 광고 선택 → 광고 추적 제한 해제\n" +
                "(OS버전에 따라 다소 상이할 수 있음)\n" +
                "⑤ 행태정보 관련 문의 및 피해구제 방법\n" +
                "관련부서 및 고객문의 : 렛잇고 고객센터(070-8065-0451)\n" +
                "나. 회사는 이용자의 웹사이트 방문기록 파악을 위해 이용정보를 저장하고 불러오는 '쿠키(cookie)'를 사용하며 해당 정보를 목적 외로 이용하거나 제3자에게 제공하지 않습니다.\n" +
                "'쿠키(cookie)'는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자의 PC내의 하드디스크에 저장되기도 합니다.\n" +
                "3. 쿠키의 사용 목적\n" +
                "이용자의 접속빈도, 방문시간 등 웹사이트 방문 기록을 파악하여 이용자에게 최적화된 정보를 제공하는데 사용 됩니다.\n" +
                "4. 쿠키의 설치·운영 및 거부\n" +
                "웹브라우저 상단의 '도구 > 인터넷 옵션 > 개인정보' 메뉴의 옵션설정을 통해 쿠키저장을 거부할 수 있습니다. 단, 고객이 쿠키의 저장을 거부하는 옵션을 선택하시는 경우에는 서비스 이용에 불편이 야기될 수 있습니다.\n" +
                "5. 개인정보 안전성 확보 조치\n" +
                "회사는 개인정보를 처리함에 있어 개인정보가 분실, 도난, 유출, 변조 또는 훼손되지 않도록 안전하게 처리하기 위하여 다음과 같은 기술적, 관리적 조치를 강구하고 있습니다.\n" +
                "\n" +
                "가. 내부관리계획의 수립 및 시행\n" +
                "회사는 개인정보의 안전한 처리를 위하여 내부 정책을 수집하여 운영하고 있으며, 정보보안 감사 등을 통해 개인정보 보호조치의 이행사항을 확인하여 문제점 발견 시 즉시 개선을 하도록 조치하고 있습니다.\n" +
                "나. 접근통제장치의 설치 및 운영\n" +
                "회사는 침입차단시스템 등 보안솔루션을 이용하여 외부로부터 무단 접근을 통제하고 있으며, 보안성을 확보하고자 기술적 장치를 갖추고자 하고 있습니다.\n" +
                "다. 접속기록의 위조, 변조 방지를 위한 조치\n" +
                "회사는 개인정보처리시스템에 접속한 기록을 보관, 관리하고 있으며, 접속기록이 위조, 변조되지 않도록 관리하고 있습니다.\n" +
                "라. 개인정보의 암호화\n" +
                "회사는 이용자의 개인정보를 암호화된 통신구간을 이용하여 전송하고, 비밀번호 등 중요정보는 암호화하여 보관하고 있습니다.\n" +
                "마. 해킹 등에 대비한 대책\n" +
                "회사는 해커 등의 침입을 탐지하고 차단할 수 있는 시스템을 통하여 개인정보를 24시간 감시하고 있으며, 백신프로그램을 이용하여 컴퓨터바이러스에 의한 피해를 방지하기 위한 조치를 취하고 있습니다. 백신프로그램은 주기적으로 업데이트되며 갑작스런 바이러스가 출현할 경우 백신이 나오는 즉시 이를 제공함으로써 개인 정보가 침해되는 것을 방지하고 있습니다. 또한 새로운 해킹/보안 기술에 대해 지속적으로 연구하여 서비스에 적용하고 있습니다.\n" +
                "바. 취급 직원의 최소화 및 교육\n" +
                "회사는 업무 수행에 반드시 필요한 인원에 한하여 개인정보에 접근할 수 있도록 접근권한을 최소한으로 부여하며, 개인정보취급자를 대상으로 개인정보보호의 안전한 관리에 대한 정기적인 교육을 수행합니다.\n" +
                "회사는, 정보유출을 사전에 방지하기 위하여, 직원으로 하여금 입사시 보안서약서를 작성하도록 하고, 개인정보취급자의 업무는 보안이 철저히 유지된 상태에서 인수인계가 이루어지며, 퇴사 후에도 책임소재를 철저히 파악하고 있습니다. 회사는, 직원의 개인정보보호정책 준수 여부를 감사하기 위한 내부절차를 마련하여 개인정보를 보호하고자 하고 있습니다.\n" +
                "\n" +
                "10. 개인정보 보호책임자 및 고충사항 처리부서\n" +
                "\n" +
                "담당 : 권혁문(대표)\n" +
                "\n" +
                "민원처리 고객센터:070-8065-0451\n" +
                "\n" +
                "회사는 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 이용자의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.\n");
        TERMS.put("locationBasedServiceUse", "제 1 조 (목적)\n" +
                "본 약관은 이용자(렛잇고 서비스를 이용하는 모든 사람을 말합니다. 이하 “이용자”라고 합니다.)가 주식회사 와이낫어스(이하 “회사”라고 합니다.)가 제공하는 렛잇고 위치기반서비스(이하 “서비스”라고 합니다.)를 이용함에 있어 회사와 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.\n" +
                "\n" +
                "제 2 조 (이용약관의 효력 및 변경)\n" +
                "① 본 약관은 렛잇고 서비스를 통해 본 약관에 동의함으로써 효력이 발생합니다.\n" +
                "② 이용자가 렛잇고 서비스를 통해 본 약관의 “동의하기” 버튼을 클릭하였을 경우, 본 약관의 내용을 모두 읽고 이를 충분히 이해하였으며, 그 적용에 동의한 것으로 봅니다.\n" +
                "③ 회사는 위치정보의 보호 및 이용 등에 관한 법률, 콘텐츠산업 진흥법, 전자상거래 등에서의 소비자보호에 관한 법률, 소비자기본법 약관의 규제에 관한 법률 등 관련 법령을 위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.\n" +
                "④ 회사가 약관을 개정할 경우에는 개정 내용 및 개정 약관의 시행일자와 개정사유를 명시하여 현행 약관과 함께 그 적용일자 10일 전부터 적용일 이후 상당한 기간 동안 렛잇고 앱 내 공지만을 이용하고, 개정 내용이 이용자에게 불리한 경우에는 그 적용일자 30일 전부터 적용일 이후 상당한 기간 동안 각각 이를 서비스 홈페이지에 게시하거나 이용자에게 전자적 형태(SMS 등)로 약관 개정 사실을 발송하여 고지합니다.\n" +
                "⑤ 이용자가 전항에 따른 회사의 공지 또는 고지일로부터 개정약관 시행일 7일 후까지 거부의사를 표시하지 아니하면 이용약관에 승인한 것으로 봅니다. 이용자가 개정약관에 동의하지 않을 경우 이용자는 이용계약을 해지할 수 있습니다.\n" +
                "\n" +
                "제 3 조 (관련 법령의 적용)\n" +
                "본 약관은 신의성실의 원칙에 따라 공정하게 적용하며, 본 약관에 명시되지 아니한 사항에 대하여는 관련 법령 또는 상관례에 따릅니다.\n" +
                "\n" +
                "제 4 조 (서비스의 내용)\n" +
                "회사가 제공하는 서비스는 아래와 같습니다.\n" +
                "\n" +
                "- 이용자의 실시간 위치확인\n" +
                "- 이용자의 위치에서 근접한 가게 정보 제공\n" +
                "\n" +
                "제 5 조 (서비스 이용요금)\n" +
                "① 회사가 제공하는 서비스는 기본적으로  무료입니다. 단, 별도의 유료 서비스의 경우 해당 서비스에 명시된 요금을 지불하여야 사용 가능합니다.\n" +
                "② 회사는 유료서비스 이용요금을 회사와 계약한 전자지불업체에서 정한 방법에 의하거나 회사가 정한 청구서에 합산하여 청구할 수 있습니다.\n" +
                "③ 유료서비스 이용을 통하여 결제된 대금에 대한 취소 및 환불은 회사의 결제 이용약관 등 관련 법령에 따릅니다.\n" +
                "④ 이용자의 정보도용 및 결제사기로 인한 환불요청 또는 결제자의 개인정보 요구는 법령에 정해진 경우 외에는 거절될 수 있습니다.\n" +
                "⑤ 무선통신 서비스 이용 시 발생하는 데이터 통신료는 회사와 무관하며 이용자가 가입한 각 이동통신사의 정책에 따릅니다. \n" +
                "\n" +
                "제 6 조 (서비스내용변경 통지 등)\n" +
                "① 회사가 서비스 내용을 변경하거나 종료하는 경우 회사는 이용자의 등록된 휴대폰 번호로 SMS를 통하여 서비스 내용의 변경 사항 또는 종료를 통지할 수 있습니다.\n" +
                "② 전항의 경우 불특정 다수인을 상대로 통지를 함에 있어서는 렛잇고 서비스 앱 또는 웹사이트 등 기타 회사의 공지사항을 통하여 이용자들에게 통지할 수 있습니다.\n" +
                "\n" +
                "제 7 조 (서비스이용의 제한 및 중지)\n" +
                "① 회사는 아래 각 호의 사유가 발생한 경우에는 이용자의 서비스 이용을 제한하거나 중지시킬 수 있습니다.\n" +
                "\n" +
                "1. 이용자가 회사 서비스의 운영을 고의 또는 중과실로 방해하는 경우\n" +
                "2. 서비스용 설비 점검, 보수 또는 공사로 인하여 부득이한 경우\n" +
                "3. 전기통신사업법에 규정된 기간통신사업자가 전기통신 서비스를 중지했을 경우\n" +
                "4. 국가비상사태, 서비스 설비의 장애 또는 서비스 이용의 폭주 등으로 서비스 이용에 지장이 있는 때\n" +
                "5. 기타 중대한 사유로 인하여 회사가 서비스 제공을 지속하는 것이 부적당하다고 인정하는 경우 회사는 전항의 규정에 의하여 서비스의 이용을 제한하거나 중지한 때에는 그 사유 및 제한기간 등을 이용자에게 알려야 합니다.\n" +
                "② 회사는 전항의 규정에 의하여 서비스의 이용을 제한하거나 중지한 때에는 특별한 사정이 없는 한 그 사유 및 제한기간 등을 이용자에게 알려야 합니다.\n" +
                "\n" +
                "제 8 조 (개인위치정보의 이용 또는 제공)\n" +
                "① 회사는 개인위치정보를 이용하여 서비스를 제공하고자 하는 경우에는 미리 이용약관에 명시한 후 개인위치정보주체의 동의를 얻어야 합니다.\n" +
                "② 회사는 타사업자 또는 이용 고객과의 요금정산 및 민원처리를 위해 위치정보 이용·제공사실 확인자료를 자동 기록. 보존하며, 해당 자료는 6개월간 보관합니다.\n" +
                "③ 회사는 개인위치정보를 이용자가 지정하는 제3자에게 제공하는 경우에는 개인위치정보를 수집한 당해 통신 단말장치로 매회 이용자에게 제공받는 자, 제공일시 및 제공목적을 즉시 통보합니다. 단, 아래 각 호에 해당하는 경우에는 이용자가 미리 특정하여 지정한 통신 단말장치 또는 전자우편주소로 통보합니다.\n" +
                "\n" +
                "1. 개인위치정보를 수집한 당해 통신단말장치가 문자, 음성 또는 영상의 수신기능을 갖추지 아니한 경우\n" +
                "2. 이용자가 다른 방법으로 통보할 것을 미리 요청한 경우\n" +
                "\n" +
                "제 9 조 (개인위치정보의 보유목적 및 보유기간)\n" +
                "① 회사는 위치기반서비스 제공 이후 이용자의 개인위치정보를 별도로 보유하지 않습니다. 보유하고자 하는 경우에는 보유목적 및 보유기간을 미리 이용약관에 명시한 후 동의를 얻습니다.\n" +
                "\n" +
                "제 10 조 (개인위치정보주체의 권리)\n" +
                "① 이용자는 회사에 대하여 언제든지 개인위치정보를 이용한 위치기반서비스 제공 및 개인위치 정보의 제3자 제공에 대한 동의의 전부 또는 일부를 철회할 수 있습니다.\n" +
                "② 이용자는 회사에 대하여 언제든지 개인위치정보의 수집, 이용 또는 제공의 일시적인 중지를 요구할 수 있으며, 회사는 이를 거절할 수 없고 이를 위한 기술적 수단을 갖추고 있습니다.\n" +
                "③ 이용자는 회사에 대하여 아래 각 호의 자료에 대한 열람 또는 고지를 요구할 수 있고, 당해 자료에 오류가 있는 경우에는 그 정정을 요구할 수 있습니다. 이 경우 회사는 정당한 사유 없이 이용자의 요구를 거절할 수 없습니다.\n" +
                "\n" +
                "1. 본인에 대한 위치정보 이용·제공사실 확인자료\n" +
                "2. 본인의 개인위치정보가 위치정보의 보호 및 이용 등에 관한 법률 또는 다른 법률 규정에 의하여 제 3자에게 제공된 이유 및 내용\n" +
                "\n" +
                "④ 이용자는 제1항 내지 제3항의 권리행사를 위해 다음의 연락처를 통해 요구할 수 있습니다.\n" +
                "\n" +
                "- 렛잇고 고객센터: 070-8065-0451\n" +
                "\n" +
                "제 11 조 (법정대리인의 권리)\n" +
                "① 회사는 14세 미만의 이용자에 대해서는 개인위치정보를 이용한 위치기반서비스 제공 및 개인위치정보의 제 3자 제공에 대한 동의를 당해 이용자와 당해 이용자의 법정대리인으로부터 동의를 받아야 합니다. 이 경우 법정대리인은 제10조에 의한 이용자의 권리를 모두 가집니다.\n" +
                "② 회사는 14세 미만의 아동의 개인위치정보 또는 위치정보 이용.제공사실 확인자료를 이용 약관에 명시 또는 고지한 범위를 넘어 이용하거나 제3자에게 제공하고자 하는 경우에는 14세미만의 아동과 그 법정대리인의 동의를 받아야 합니다. 단, 아래의 경우는 제외합니다.\n" +
                "• 위치정보 및 위치기반서비스 제공에 따른 요금정산을 위하여 위치정보 이용, 제공사실 확인자료가 필요한 경우\n" +
                "• 통계작성, 학술연구 또는 시장조사를 위하여 특정 개인을 알아볼 수 없는 형태로 가공하여 제공하는 경우\n" +
                "\n" +
                "제 12 조 (위치정보관리책임자의 지정)\n" +
                "① 회사는 위치정보를 적절히 관리, 보호하고 개인위치정보주체의 불만을 원활히 처리할 수 있도록 실질적인 책임을 질 수 있는 지위에 있는 자를 위치정보관리 책임자로 지정해 운영합니다.\n" +
                "② 위치정보관리책임자는 위치기반 서비스를 제공하는 부서의 부서장으로서 구체적인 사항은 본 약관의 부칙에 따릅니다.\n" +
                "\n" +
                "제 13 조 (이용자의 대금지급방법)\n" +
                "① 회사가 위치정보의 보호 및 이용 등에 관한 법률 제 15조 내지 제 26조의 규정을 위반한 행위로 이용자에게 손해가 발생한 경우 이용자는 회사에 대하여 손해배상 청구를 할 수 있습니다. 이 경우 회사는 고의, 과실이 없음을 입증하지 못 하는 경우 책임을 면할 수 없습니다.\n" +
                "② 이용자가 본 약관의 규정을 위반하여 회사에 손해가 발생한 경우 회사는 이용자에 대하여 손해배상을 청구할 수 있습니다. 이 경우 이용자는 고의, 과실이 없음을 입증하지 못 하는 경우 책임을 면할 수 없습니다.\n" +
                "③ 손해배상의 청구는 회사에 청구사유, 청구금액 및 산출근거를 기재하여 서면으로 하여야 합니다.\n" +
                "\n" +
                "제 14 조 (면책)\n" +
                "① 회사는 다음 각 호의 경우로 서비스를 제공할 수 없는 경우 이로 인하여 이용자에게 발생한 손해에 대해서는 책임을 부담하지 않습니다.\n" +
                "1. 천재지변 또는 이에 준하는 불가항력의 상태가 있는 경우\n" +
                "2. 서비스 제공을 위하여 회사와 서비스 제휴계약을 체결한 제3자의 고의적인 서비스 방해가 있는 경우\n" +
                "3. 이용자의 귀책사유로 서비스 이용에 장애가 있는 경우\n" +
                "4. 제1호 내지 제 3호를 제외한 기타 회사의 고의.과실이 없는 사유로 인한 경우\n" +
                "\n" +
                "② 회사는 서비스 및 서비스에 게재된 정보, 자료, 사실의 신뢰도, 정확성 등에 대해서는 보증을 하지 않으며 이로 인해 발생한 이용자의 손해에 대하여는 책임을 부담하지 아니합니다.\n" +
                "\n" +
                "제 15 조 (규정의 준용보충)\n" +
                "① 본 약관은 대한민국 법령에 의하여 해석합니다.\n" +
                "② 본 약관에 규정되지 않은 사항에 대해서는 관련 법령 및 상관습에 의합니다.\n" +
                "③ 본 약관에서 정의되지 않은 용어는 렛잇고 이용약관, 앱 내 안내, 관련 법령 및 사전적·통상적 의미에 따릅니다.\n" +
                "\n" +
                "제 16 조 (분쟁의 조정 및 기타)\n" +
                "① 회사는 위치정보와 관련된 분쟁에 대해 당사자간 협의가 이루어지지 아니하거나 협의를 할 수 없는 경우에는 위치정보법 제28조에 따라 방송통신위원회에 재정을 신청할 수 있습니다.\n" +
                "② 회사 또는 이용자는 위치정보와 관련된 분쟁에 대해 당사자간 협의가 이루어지지 아니하거나 협의를 할 수 없는 경우에는 개인정보보호법 제43조에 따라 개인정보분쟁조정위원회에 조정을 신청할 수 있습니다.\n" +
                "\n" +
                "제 17 조 (회사의 연락처)\n" +
                "회사의 상호 및 주소 등은 다음과 같습니다.\n" +
                "1. 상호 : 주식회사 와이낫어스\n" +
                "2. 대표자: 권혁문\n" +
                "3. 주소 : 서울 서초구 남부순환로339길 60 402호\n" +
                "4. 대표전화: 070-8065-0451\n" +
                "\n" +
                "부칙\n" +
                "제 1조 (시행일) 이 약관은 2023년 6월 5일부터 시행한다.");
        TERMS.put("collectPersonalInfoForEvent", "1. 개인정보 수집 ∙ 이용 항목\n" +
                "이메일, 휴대전화번호, 닉네임, 성별, 생일, 연령대, 지역, CI(연계정보), DI(중복가입확인정보), 서비스 이용 기록, 접속 로그, 쿠키, 접속 IP 주소, 기기정보(모델명, OS정보, 기기고유번호), 광고ID\n" +
                "\n" +
                "2. 개인정보 수집 ∙ 이용 목적\n" +
                "(광고성) 마케팅, 프로모션 및 혜택 소식 전송\n" +
                "\n" +
                "3. 개인정보 보유 ∙ 이용 기간\n" +
                "회원탈퇴 시 또는 동의 철회 시 까지\n" +
                "\n" +
                "4. 동의 거부 권리 및 거부 시 불이익\n" +
                "개인정보 수집 및 이용에 동의하지 않을 권리가 있으며, 동의를 거부 할 경우 회원가입 및 서비스 이용에 제한이 없으나 혜택 알림 등을 받으실 수 없습니다.");
        TERMS.put("receiveAd", "1. 개인정보 수집 ∙ 이용 항목\n" +
                "이메일, 휴대전화번호, 닉네임, 성별, 생일, 연령대, 지역, CI(연계정보), DI(중복가입확인정보), 서비스 이용 기록, 접속 로그, 쿠키, 접속 IP 주소, 기기정보(모델명, OS정보, 기기고유번호), 광고ID\n" +
                "\n" +
                "2. 개인정보 수집 ∙ 이용 목적\n" +
                "(광고성) 마케팅, 프로모션 및 혜택 소식 전송\n" +
                "\n" +
                "3. 개인정보 보유 ∙ 이용 기간\n" +
                "회원탈퇴 시 또는 동의 철회 시 까지\n" +
                "\n" +
                "4. 동의 거부 권리 및 거부 시 불이익\n" +
                "개인정보 수집 및 이용에 동의하지 않을 권리가 있으며, 동의를 거부 할 경우 회원가입 및 서비스 이용에 제한이 없으나 혜택 알림 등을 받으실 수 없습니다.");
    }
}
