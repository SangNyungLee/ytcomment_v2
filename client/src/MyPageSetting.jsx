import { Lock, Mail, Save, User } from "lucide-react";
import { Card, CardContent } from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";

export default function SettingsPage() {
  return (
    <div className="max-w-4xl mx-auto p-6">
      <h1 className="text-2xl font-bold mb-4">설정</h1>

      <Card className="mb-6">
        <CardContent className="p-4 space-y-4">
          <div className="flex items-center gap-3">
            <User size={20} className="text-gray-600" />
            <Input type="text" placeholder="이름" defaultValue="사용자 이름" />
          </div>
          <div className="flex items-center gap-3">
            <Mail size={20} className="text-gray-600" />
            <Input type="email" placeholder="이메일" defaultValue="user@example.com" disabled />
          </div>
          <div className="flex items-center gap-3">
            <Lock size={20} className="text-gray-600" />
            <Input type="password" placeholder="새 비밀번호 입력" />
          </div>
        </CardContent>
      </Card>

      <Button variant="default" className="flex items-center gap-2">
        <Save size={20} /> 변경사항 저장
      </Button>
    </div>
  );
}
