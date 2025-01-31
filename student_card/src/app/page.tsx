import Image from "next/image";
import styles from "./page.module.css";
import OneCard from "@/components/OneCard";
import {Col, Row} from "react-bootstrap";

export default function Home() {
  return (
<Row>
  <Col>
    <OneCard front="valami" back="csdcwe" />
  </Col>
</Row>

  );
}
