import { User, Bookmark, Settings, LogOut } from "lucide-react";
import { Card, CardContent } from "@/components/ui/card";
import { Button } from "@/components/ui/button";
import "../src/css/MyPage.css";

const MyPage = () => {
  return (
    <div className="myPageWrapper">
      <div className="myPageContent">
        <h1 className="text-2xl font-bold mb-4 text-center">마이페이지</h1>

        <Card className="mb-6">
          <CardContent className="flex items-center gap-4 p-4">
            <User size={40} className="text-gray-600" />
            <div>
              <p className="text-lg font-semibold">사용자 이름</p>
              <p className="text-gray-500">user@example.com</p>
              <p className="text-gray-400 text-sm">가입일: 2024-01-01</p>
            </div>
          </CardContent>
        </Card>

        <h2 className="text-xl font-semibold mb-3">내 스크랩 목록</h2>
        <div className="space-y-3">
          <Card>
            <CardContent className="flex items-center gap-4 p-4">
              <Bookmark size={24} className="text-gray-600" />
              <p>스크랩한 영상 1</p>
            </CardContent>
          </Card>
          <Card>
            <CardContent className="flex items-center gap-4 p-4">
              <Bookmark size={24} className="text-gray-600" />
              <p>스크랩한 영상 2</p>
            </CardContent>
          </Card>
        </div>

        <div className="mt-6 flex justify-between">
          <Button variant="outline" className="flex items-center gap-2">
            <Settings size={20} /> 설정
          </Button>
          <Button variant="destructive" className="flex items-center gap-2">
            <LogOut size={20} /> 로그아웃
          </Button>
        </div>
      </div>
    </div>
  );
}

export default MyPage;