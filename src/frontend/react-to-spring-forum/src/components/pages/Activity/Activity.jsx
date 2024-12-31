import { useContext } from "react";
import ActivityList from "../../organisms/ActivityList/ActivityList";
import s from "./style.module.css";
import { NotificationContext } from "../../../context/NotificationContext";

const Activity = () => {
  const notificationHandle = useContext(NotificationContext);
  notificationHandle.setHasActivityNotification(false);
  return (
    <div className={s.container}>
      <ActivityList />
    </div>
  );
};

export default Activity;
